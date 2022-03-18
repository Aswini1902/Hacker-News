package com.ashandroid.showcase.hnews.ui.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ashandroid.showcase.hnews.StoriesViewModel
import androidx.fragment.app.activityViewModels
import com.ashandroid.showcase.hnews.databinding.FragmentTopStoryDetailBinding
import kotlinx.android.synthetic.main.fragment_top_story_detail.*

class TopStoryDetailFragment : Fragment() {

    private val viewModel: StoriesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Declare the variable for viewBinding, it interact with UI elements in layout file
        var binding = FragmentTopStoryDetailBinding.inflate(inflater)

        // Create a variable to get data in detail from viewModel
        var story = viewModel.StoriesData.value

        // The lifecycleOwner should be used for observing changes of LiveData in this binding.
        binding.lifecycleOwner = this

        // Set the data(story) with dataBinding variable
        binding.topStories = story

        return binding.root
    }

    override fun onResume() {
        url.setOnClickListener {
            viewModel.onUrlClicked()
        }
        super.onResume()
    }

}



