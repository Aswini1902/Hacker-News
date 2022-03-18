package com.ashandroid.showcase.hnews.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.ashandroid.showcase.hnews.StoriesViewModel
import com.ashandroid.showcase.hnews.databinding.FragmentUrlBinding
import com.ashandroid.showcase.hnews.generated.callback.OnClickListener
import kotlinx.android.synthetic.main.fragment_url.*
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ashandroid.showcase.hnews.R

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

    override fun onResume() {
        super.onResume()
        close.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }

    }

}