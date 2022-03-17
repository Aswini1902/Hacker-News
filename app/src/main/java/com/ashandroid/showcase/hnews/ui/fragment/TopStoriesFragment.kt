package com.ashandroid.showcase.hnews.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ashandroid.showcase.hnews.StoriesViewModel
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.ashandroid.showcase.hnews.adapter.StoriesAdapter
import com.ashandroid.showcase.hnews.adapter.StoriesListener
import com.ashandroid.showcase.hnews.databinding.FragmentTopStoriesBinding
import kotlinx.android.synthetic.main.fragment_top_stories.*


class TopStoriesFragment : Fragment() {

    private var n = 0

    private val viewModel: StoriesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentTopStoriesBinding.inflate(inflater)
        // Get getTopStoriesList() from ViewModel for list of items
        viewModel.getTopStories(n)
        // lifecycleOwner – The LifecycleOwner that should be used for observing changes of LiveData in this binding.
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.recyclerView.adapter = StoriesAdapter(StoriesListener { stories ->
            viewModel.onTopStoriesClicked(stories)
        })

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        scrollListener()
    }

    private fun scrollListener() {
        recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1)) {
                    n++
                    viewModel.getTopStories(n)
                }
            }
        })
    }
}


