package com.ashandroid.showcase.hnews.ui.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ashandroid.showcase.hnews.StoriesViewModel
import androidx.fragment.app.activityViewModels
import com.ashandroid.showcase.hnews.databinding.FragmentTopStoryDetailBinding
import com.ashandroid.showcase.hnews.model.Stories
import com.ashandroid.showcase.hnews.utils.HTMLScrapper
import kotlinx.android.synthetic.main.fragment_top_story_detail.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

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

        val unixTime = (story?.time?:0).toLong()

        binding.parsedTime = viewModel.getDateString(unixTime)

        parseUrlForPreview(binding, story)


        return binding.root
    }

    private fun parseUrlForPreview(
        binding: FragmentTopStoryDetailBinding,
        story: Stories?
    ) {
        //Set the utility to parse HTML content
        MainScope().launch(Dispatchers.IO) {

            story?.url?.let {
                HTMLScrapper().parseContent(it, { preview ->
                    binding.htmlPreview = preview

                })
            }
        }
    }

    override fun onResume() {
        url.setOnClickListener {
            viewModel.onUrlClicked()
        }
        super.onResume()
    }

}



