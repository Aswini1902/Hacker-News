package com.ashandroid.showcase.hnews.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ashandroid.showcase.hnews.model.Stories
import com.ashandroid.showcase.hnews.databinding.TopStoriesItemBinding

class StoriesAdapter( val clickListener: StoriesListener) : ListAdapter<Stories,StoriesAdapter.StoriesViewHolder>(DiffCallback) {

   companion object DiffCallback : DiffUtil.ItemCallback<Stories>() {
       override fun areItemsTheSame(oldItem: Stories, newItem: Stories): Boolean {
           return oldItem == newItem
       }
       override fun areContentsTheSame(oldItem: Stories, newItem: Stories): Boolean {
            return oldItem == newItem
       }
   }

    class StoriesViewHolder(private var binding: TopStoriesItemBinding,
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: StoriesListener, stories: Stories) {   //Int type
            binding.topStories = stories
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }
    // onCreateViewHolder is called by layout manager to create new viewHolder for the RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoriesViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
        return StoriesViewHolder(
            TopStoriesItemBinding.inflate(adapterLayout, parent, false)
        )
    }
    // onBindViewHolder is called by layout manager in order to replace the contents of list item view
    override fun onBindViewHolder(holder: StoriesViewHolder, position: Int) {
        val item = getItem(position)     //getItem(position)
        holder.bind(clickListener, item)
    }
}

// Create a class for an item in holder which is hold the data.
// It can be bind with recyclerView in Fragment
class StoriesListener(val clickListener: (story: Stories) -> Unit ) {
    fun onClick(story: Stories) = clickListener(story)
    fun getTimeString(unixTimeSeconds: Long):String?{
        val currentTime = System.currentTimeMillis()
        val unixTimeMilliSeconds = unixTimeSeconds*1000
        val timediffMs = currentTime - unixTimeMilliSeconds

        val timeDiffSeconds = timediffMs/1000
        val timeDiffMinutes = timeDiffSeconds/60
        val timeDiffHrs = timeDiffMinutes/60
        val timeDiffDays = timeDiffHrs/24
        val timeDiffWeeks = timeDiffDays/7


        return when {
            timeDiffWeeks > 0 -> "$timeDiffWeeks weeks ago"
            timeDiffDays > 0 -> "$timeDiffDays days ago"
            timeDiffHrs > 0 -> "$timeDiffHrs hours ago"
            timeDiffMinutes > 0 -> "$timeDiffMinutes mins ago"
            else -> "$timeDiffSeconds secs ago"
        }

    }
}


