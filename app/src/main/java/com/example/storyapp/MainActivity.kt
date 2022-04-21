package com.example.storyapp

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storyapp.data.database.StoryFirebase
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var storyTitles = arrayListOf<String>()
    private var storyContents = arrayListOf<String>()
    private var storyImages = arrayListOf<String>()

//    val toolbar= findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
//    val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
//    val navigationView = findViewById<NavigationView>(R.id.navigationView)
//    val storyList = findViewById<RecyclerView>(R.id.storyList)

    private lateinit var database :DatabaseReference
    private var storyFirebase = arrayListOf<StoryFirebase>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toggle = ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close)
        toggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navigationView.setNavigationItemSelectedListener(this)
        storyList.layoutManager = LinearLayoutManager(this)
        storyList.setHasFixedSize(true)

        database= FirebaseDatabase.getInstance().getReference("/stories")
        database.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val story = userSnapshot.getValue(StoryFirebase::class.java)
                        storyFirebase.add(story!!)
                        Log.e("TAG", "Title : ${storyFirebase[0].title}")
                        Log.e("TAG", "size : ${storyFirebase.size}", )
                    }
                    for (i in 0 until storyFirebase.size){
                        storyTitles.add(storyFirebase[i].title!!)
                        storyContents.add(storyFirebase[i].content!!)
                        storyImages.add(storyFirebase[i].image!!)
                        Log.e("TAG", "onDataChange: ${storyTitles[i]} && ${storyFirebase[i].title!!}")
                    }
                    val adapter = ItemAdapter(storyTitles,storyContents,storyImages)
                    storyList.adapter = adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
//        storyTitles = resources.getStringArray(R.array.storyTitles)
//        storyContents = resources.getStringArray(R.array.storyContents)
//        storyImages = resources.getStringArray(R.array.storyImages)

//        val adapter = ItemAdapter(storyTitles,storyContents,storyImages)
//        storyList.adapter = adapter

        img_add.setOnClickListener{
            val intent =Intent(applicationContext,AddStoryActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawerLayout.closeDrawer(GravityCompat.START)
        if(item.itemId == R.id.random){
            val randPosition = Random.nextInt(0,storyTitles.size)
            val intent = Intent(applicationContext,DetailsActivity::class.java)
            intent.putExtra("storyTitle",storyTitles[randPosition])
            intent.putExtra("storyContent",storyContents[randPosition])
            intent.putExtra("storyImage",storyImages[randPosition])
            startActivity(intent)

        }
        if(item.itemId == R.id.stories){
            //Toast.makeText(this,"Will be added soon!!",Toast.LENGTH_SHORT).show()
            val intent =Intent(applicationContext,AddStoryActivity::class.java)
            startActivity(intent)
        }
        if(item.itemId == R.id.rate) {

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Rate Us")
                .setItems(R.array.rate_array,DialogInterface.OnClickListener{ _, _ ->
                    Toast.makeText(this, "Thank You For rating Us!!", Toast.LENGTH_SHORT).show()
                })
//            builder.setPositiveButton("Rate",DialogInterface.OnClickListener{ _, _ ->
//                Toast.makeText(this, "Thank You For rating Us!!", Toast.LENGTH_SHORT).show()
//            })
            builder.setNegativeButton("Cancel",null)
            builder.show()

        }
        if(item.itemId == R.id.contact)
            Toast.makeText(this,"Our team will contact you soon!!",Toast.LENGTH_SHORT).show()


        return true
    }
}