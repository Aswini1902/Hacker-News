package com.ashandroid.showcase.hnews.ui.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.ashandroid.showcase.hnews.R
import com.ashandroid.showcase.hnews.StoriesViewModel
import com.ashandroid.showcase.hnews.ui.detail.JobStoryDetailFragment
import com.ashandroid.showcase.hnews.ui.detail.TopStoryDetailFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val viewModel: StoriesViewModel by viewModels()

    val topStoriesFragment = TopStoriesFragment()
    val jobStoriesFragment = JobStoriesFragment()
    val askStoriesFragment = AskStoriesFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
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

    override fun onResume() {

        viewModel.showDetail.observe(this,{
            if(it){

                // Create an instance of transaction from the supportFragmentManager
                    // by calling beginTransaction()
                // supportFragmentManager - Return the FragmentManager for interacting with fragments
                    // associated with this activity.
                val transaction = this.supportFragmentManager.beginTransaction()

                // Create an instance of topStoryDetailFragment for new fragment
                val topStoryDetailFragment = TopStoryDetailFragment()

                if (topStoryDetailFragment == TopStoryDetailFragment()) {

                    //Use replace() to replace an existing fragment in a container
                    // with an instance of a new fragment class that you provide.
                    transaction.replace(R.id.hacker_news, topStoryDetailFragment)
                } else {
                    val jobStoryDetailFragment = JobStoryDetailFragment()
                    transaction.replace(R.id.hacker_news, jobStoryDetailFragment)
                }

                //Can save each transaction to a back stack managed by the FragmentManager
                //allowing the user to navigate backward through the fragment changes
                transaction.addToBackStack(topStoryDetailFragment::class.simpleName)

                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)

                //The commit() call signals to the FragmentManager
                // that all operations have been added to the transaction.
                transaction.commit()
            }
        })
        super.onResume()
    }

    override fun onBackPressed() {

        // backStackEntryCount means number of fragments in the back stack
        val count = supportFragmentManager.backStackEntryCount
        if (count == 1) {
            // popBackStack() - Pop the top state off the back stack. (or)
                // Remove the top of the fragment in the stack based on count
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}

