package com.ashandroid.showcase.hnews

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ashandroid.showcase.hnews.adapter.TopStoriesAdapter
import com.ashandroid.showcase.hnews.model.Stories

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Stories>?) {
    val adapter = recyclerView.adapter as TopStoriesAdapter
    adapter.submitList(data)
}

@BindingAdapter("apiStatus")
fun bindStatus(statusImageView: ImageView, status: TopStoriesApiStatus?) {
    when(status) {
        TopStoriesApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
        }
        TopStoriesApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        TopStoriesApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
        }
    }
}


