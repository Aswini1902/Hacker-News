package com.ashandroid.showcase.hnews.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ashandroid.showcase.hnews.databinding.FragmentAboutUsBinding
import kotlinx.android.synthetic.main.fragment_about_us.*

class AboutUsFragment : Fragment() {

    val linkedIn = "https://www.linkedin.com/in/aswini-srinivasan-811143222/"
    val gitHub = "https://github.com/Aswini1902/"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentAboutUsBinding.inflate(inflater)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        linked_in.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(linkedIn))
            startActivity(intent)
        }

        github.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(gitHub))
            startActivity(intent)
        }
    }
}