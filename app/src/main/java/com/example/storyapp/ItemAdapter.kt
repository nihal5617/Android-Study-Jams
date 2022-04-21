package com.example.storyapp

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ItemAdapter(val storyTitles: ArrayList<String>, val storyContents: ArrayList<String>, val storyImages: ArrayList<String>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardTitle: TextView = itemView.findViewById(R.id.cardTitle)
        val cardContent: TextView = itemView.findViewById(R.id.cardContent)
        val cardImage : ImageView = itemView.findViewById(R.id.cardImage)
        val view = itemView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_item_view,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.e("TAG", "title : is here ")
        holder.cardTitle.text = storyTitles[position]
        Log.e("TAG", "title : ${storyTitles[position]} ")
        holder.cardContent.text = storyContents[position]
        Picasso.get().load(storyImages[position]).into(holder.cardImage)

        holder.view.setOnClickListener{
//            Toast.makeText(holder.view.context,"Item Number -> " + position,Toast.LENGTH_SHORT).show()
            val intent = Intent(it.context,DetailsActivity::class.java)
            intent.putExtra("storyTitle",storyTitles[position])
            intent.putExtra("storyContent",storyContents[position])
            intent.putExtra("storyImage",storyImages[position])
            holder.view.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return storyTitles.size
    }
}