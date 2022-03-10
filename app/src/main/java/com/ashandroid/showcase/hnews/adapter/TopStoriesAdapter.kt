package com.ashandroid.showcase.hnews.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ashandroid.showcase.hnews.model.Stories
import com.ashandroid.showcase.hnews.databinding.TopStoriesItemBinding



class TopStoriesAdapter(val clickListener: StoriesListener) : ListAdapter<Stories,TopStoriesAdapter.TopStoriesViewHolder>(DiffCallback) {

   companion object DiffCallback : DiffUtil.ItemCallback<Stories>() {
       override fun areItemsTheSame(oldItem: Stories, newItem: Stories): Boolean {
           return oldItem == newItem
       }

       override fun areContentsTheSame(oldItem: Stories, newItem: Stories): Boolean {
            return oldItem == newItem
       }

   }

    class TopStoriesViewHolder(private var binding: TopStoriesItemBinding,
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: StoriesListener, stories: Stories) {   //Int type
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
        val item = getItem(position)     //getItem(position)
        holder.bind(clickListener, item)
    }

}

// Create a class for an item in holder which is hold the data.
// It can be bind with recyclerView in Fragment
class StoriesListener(val clickListener: (story: Stories) -> Unit ) {
    fun onClick(story: Stories) = clickListener(story)
}


