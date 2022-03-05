package com.ashandroid.showcase.hnews.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ashandroid.showcase.hnews.R
import com.ashandroid.showcase.hnews.model.Stories
import com.ashandroid.showcase.hnews.adapter.TopStoriesAdapter
import com.ashandroid.showcase.hnews.databinding.TopStoriesItemBinding



class TopStoriesAdapter(val clickListener: StoriesListener) : ListAdapter<Int,TopStoriesAdapter.TopStoriesViewHolder>(DiffCallback) {

   companion object DiffCallback : DiffUtil.ItemCallback<Int>() {
       override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
           return oldItem == newItem
       }

       override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
       }

   }

    class TopStoriesViewHolder(private var binding: TopStoriesItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: StoriesListener, stories: Int) {
            binding.topStories = stories
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }
    // onCreateViewHolder is called by layout manager to create new viewHolder for the RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopStoriesViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
        return TopStoriesViewHolder(
            TopStoriesItemBinding.inflate(adapterLayout, parent, false)
        )
    }
    // onBindViewHolder is called by layout manager in order to replace the contents of list item view
    override fun onBindViewHolder(holder: TopStoriesViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clickListener, item)
    }

}

class StoriesListener(val clickListener: (story: Int) -> Unit ) {
    fun onClick(story: Int) = clickListener(story)
}


