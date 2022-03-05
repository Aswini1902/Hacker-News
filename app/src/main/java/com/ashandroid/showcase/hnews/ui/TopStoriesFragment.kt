package com.ashandroid.showcase.hnews.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ashandroid.showcase.hnews.StoriesViewModel
import androidx.fragment.app.activityViewModels
import com.ashandroid.showcase.hnews.adapter.StoriesListener
import com.ashandroid.showcase.hnews.adapter.TopStoriesAdapter
import com.ashandroid.showcase.hnews.databinding.FragmentTopStoriesBinding
import com.ashandroid.showcase.hnews.model.Stories


class TopStoriesFragment : Fragment() {

    private val viewModel: StoriesViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentTopStoriesBinding.inflate(inflater)
        // Get getTopStoriesList() from ViewModel for list of items
        viewModel.getTopStoriesList()
        // lifecycleOwner – The LifecycleOwner that should be used for observing changes of LiveData in this binding.
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.recyclerView.adapter = TopStoriesAdapter(StoriesListener { stories ->
            viewModel.onTopStoriesClicked(stories)
        })

        return binding.root

    }

}

//         android:onClick="@{() -> clickListener.onClick(topStories)}">