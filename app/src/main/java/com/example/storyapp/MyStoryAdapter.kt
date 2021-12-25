package com.example.storyapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.storyapp.data.database.Stories
import com.example.storyapp.databinding.ActivityAddStoryBinding
import com.example.storyapp.databinding.ShortStoryViewBinding
import com.example.storyapp.generated.callback.OnClickListener

class MyStoryAdapter(private val storylist : List<Stories>,
                     private val clickListener: (Stories)->Unit) : RecyclerView.Adapter<MyStoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyStoryViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding : ShortStoryViewBinding = DataBindingUtil.inflate(view,R.layout.short_story_view,parent,false)
        return MyStoryViewHolder(binding)
    }


    override fun getItemCount(): Int {
        return storylist.size
    }

    override fun onBindViewHolder(holder: MyStoryViewHolder, position: Int) {
        holder.bind(storylist[position],clickListener)
    }
}

class MyStoryViewHolder(val binding: ShortStoryViewBinding):RecyclerView.ViewHolder(binding.root){
    fun bind(mystory : Stories,clickListener: (Stories)->Unit){
        binding.title.text=mystory.storytitle
        binding.content.text=mystory.content
        binding.itemLayout.setOnClickListener{
            clickListener(mystory)
        }
    }
}