package com.example.storyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val sTitle = intent.getStringExtra("storyTitle")
        val sContent = intent.getStringExtra("storyContent")
        val sImage = intent.getStringExtra("storyImage")

        supportActionBar?.title = sTitle
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val storyFeatureImage = findViewById<ImageView>(R.id.storyFeatureImage)
        Picasso.get().load(sImage).into(storyFeatureImage)
        val storyDetails = findViewById<TextView>(R.id.storyDetails)
        storyDetails.text = sContent


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}