package com.ashandroid.showcase.hnews.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.ashandroid.showcase.hnews.StoriesViewModel
import com.ashandroid.showcase.hnews.databinding.FragmentUrlBinding


class UrlFragment : Fragment() {

    private val viewModel: StoriesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentUrlBinding.inflate(inflater)

        val url = viewModel.StoriesData.value?.url

        url?.let {
            binding.webView.loadUrl(it)
        }

        return binding.root
    }
}