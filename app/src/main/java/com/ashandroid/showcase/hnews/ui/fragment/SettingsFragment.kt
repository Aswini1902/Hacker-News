package com.ashandroid.showcase.hnews.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ashandroid.showcase.hnews.R
import com.ashandroid.showcase.hnews.databinding.FragmentSettingsBinding
import com.ashandroid.showcase.hnews.databinding.FragmentTopStoriesBinding
import com.ashandroid.showcase.hnews.ui.detail.AboutUsFragment
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment() {

    val aboutUsFragment = AboutUsFragment()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSettingsBinding.inflate(inflater)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        about.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction()
                .replace(com.ashandroid.showcase.hnews.R.id.hacker_news, aboutUsFragment)
                .commit()
        }
    }
}