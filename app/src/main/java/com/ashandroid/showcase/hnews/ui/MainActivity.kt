package com.ashandroid.showcase.hnews.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ashandroid.showcase.hnews.AskStoriesFragment
import com.ashandroid.showcase.hnews.JobStoriesFragment
import com.ashandroid.showcase.hnews.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val topStoriesFragment = TopStoriesFragment()
        val jobStoriesFragment = JobStoriesFragment()
        val askStoriesFragment = AskStoriesFragment()

        setCurrentFragment(topStoriesFragment)
        //Use NavigationBarView.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener) instead.
        bottom_navigation_view.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.topStories -> setCurrentFragment(topStoriesFragment)
                R.id.jobStories -> setCurrentFragment(jobStoriesFragment)
                R.id.askStories -> setCurrentFragment(askStoriesFragment)
            }
            true
        }
    }
    private fun setCurrentFragment(fragment:Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.hacker_news,fragment)
            commit()
        }
}

