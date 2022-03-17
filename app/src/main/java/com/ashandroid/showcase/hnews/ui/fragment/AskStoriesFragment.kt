package com.ashandroid.showcase.hnews.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ashandroid.showcase.hnews.StoriesViewModel
import androidx.fragment.app.activityViewModels
import com.ashandroid.showcase.hnews.adapter.StoriesAdapter
import com.ashandroid.showcase.hnews.adapter.StoriesListener
import com.ashandroid.showcase.hnews.databinding.FragmentAskStoriesBinding

class AskStoriesFragment : Fragment() {

    private val viewModel: StoriesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentAskStoriesBinding.inflate(inflater)
        // Get getAskStoriesList() from ViewModel for list of items
        viewModel.getAskStories()
        // lifecycleOwner – The LifecycleOwner that should be used for observing changes of LiveData in this binding.
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.recyclerView.adapter = StoriesAdapter(StoriesListener { stories ->
            //viewModel.onTopStoriesClicked(stories)
            viewModel.onQAClicked(stories)
        })

        return binding.root

    }

}

