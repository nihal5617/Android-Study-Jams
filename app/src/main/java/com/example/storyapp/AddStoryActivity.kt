package com.example.storyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storyapp.data.database.MyAppDao
import com.example.storyapp.data.database.MyAppDb
import com.example.storyapp.data.database.Stories
import com.example.storyapp.databinding.ActivityAddStoryBinding
import com.example.storyapp.viewmodel.StoriesRepository
import com.example.storyapp.viewmodel.StoriesViewModel
import com.example.storyapp.viewmodel.StoriesViewModelFactory

class AddStoryActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAddStoryBinding
    private lateinit var storiesViewModel: StoriesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_add_story)

        val dao : MyAppDao = MyAppDb.getDatabase(application).storiesDao()
        val repository = StoriesRepository(dao)
        val factory = StoriesViewModelFactory(repository)
        storiesViewModel = ViewModelProvider(this,factory)[StoriesViewModel::class.java]
        binding.myViewModel=storiesViewModel
        binding.lifecycleOwner=this
        initMyStoryAdapter()

        supportActionBar?.title = "My Experiences"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun displaystories(){
        storiesViewModel.allmystories.observe(this, {
            Log.i("Mytag",it.toString())
            binding.myStoryList.adapter=MyStoryAdapter(it) { selectedItem: Stories ->
                listItemClicked(
                    selectedItem
                )
            }
        })
    }
    private fun initMyStoryAdapter(){
        binding.myStoryList.layoutManager = LinearLayoutManager(this)
        displaystories()
    }
    private fun listItemClicked(stories: Stories){

        val builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("Do You Want to delete this : ")
            .setPositiveButton("Delete") { _, _ ->
                storiesViewModel.deletestory(stories)
            }
            .setNegativeButton("Cancel",null)
            .show()
    }
}