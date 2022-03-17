package com.ashandroid.showcase.hnews

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ashandroid.showcase.hnews.adapter.StoriesAdapter
import com.ashandroid.showcase.hnews.model.Stories

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Stories>?) {
    val adapter = recyclerView.adapter as StoriesAdapter
    adapter.submitList(data)
}

@BindingAdapter("apiStatus")
fun bindStatus(statusImageView: ImageView, status: StoriesApiStatus?) {
    when(status) {
        StoriesApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
        }
        StoriesApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        StoriesApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
        }
    }
}


