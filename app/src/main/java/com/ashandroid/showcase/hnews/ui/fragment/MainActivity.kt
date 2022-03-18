package com.ashandroid.showcase.hnews.ui.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.ashandroid.showcase.hnews.StoriesViewModel
import com.ashandroid.showcase.hnews.ui.detail.AskStoryDetailFragment
import com.ashandroid.showcase.hnews.ui.detail.JobStoryDetailFragment
import com.ashandroid.showcase.hnews.ui.detail.TopStoryDetailFragment
import kotlinx.android.synthetic.main.activity_main.*
import com.ashandroid.showcase.hnews.ui.detail.UrlFragment


class MainActivity : AppCompatActivity() {

    val viewModel: StoriesViewModel by viewModels()

    val topStoriesFragment = TopStoriesFragment()
    val jobStoriesFragment = JobStoriesFragment()
    val askStoriesFragment = AskStoriesFragment()
    val settingsFragment = SettingsFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(com.ashandroid.showcase.hnews.R.layout.activity_main)
        setCurrentFragment(topStoriesFragment)

        //Use NavigationBarView.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener) instead.
        bottom_navigation_view.setOnNavigationItemSelectedListener {
            when(it.itemId){
                com.ashandroid.showcase.hnews.R.id.topStories -> setCurrentFragment(topStoriesFragment)
                com.ashandroid.showcase.hnews.R.id.jobStories -> setCurrentFragment(jobStoriesFragment)
                com.ashandroid.showcase.hnews.R.id.askStories -> setCurrentFragment(askStoriesFragment)
                com.ashandroid.showcase.hnews.R.id.settings -> setCurrentFragment(settingsFragment)
            }
            true
        }

    }
    private fun setCurrentFragment(fragment:Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(com.ashandroid.showcase.hnews.R.id.hacker_news,fragment)
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

                //Use replace() to replace an existing fragment in a container
                    // with an instance of a new fragment class that you provide.
                transaction.replace(com.ashandroid.showcase.hnews.R.id.hacker_news, topStoryDetailFragment)

                //Can save each transaction to a back stack managed by the FragmentManager
                //allowing the user to navigate backward through the fragment changes
                transaction.addToBackStack(topStoryDetailFragment::class.simpleName)

                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)

                //The commit() call signals to the FragmentManager
                // that all operations have been added to the transaction.
                transaction.commit()
            }
        })

        viewModel.showJobDetail.observe(this,{
            if(it){
                val transaction = this.supportFragmentManager.beginTransaction()

                // Create an instance of jobStoryDetailFragment for new fragment
                val jobStoryDetailFragment = JobStoryDetailFragment()

                //Use replace() to replace an existing fragment in a container
                // with an instance of a new fragment class that you provide.
                transaction.replace(com.ashandroid.showcase.hnews.R.id.hacker_news, jobStoryDetailFragment)

                transaction.addToBackStack(jobStoryDetailFragment::class.simpleName)

                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)

                transaction.commit()
            }
        })

        viewModel.showQADetail.observe(this, {
            if(it){
                val transaction = this.supportFragmentManager.beginTransaction()

                // Create an instance of askStoryDetailFragment for new fragment
                val askStoryDetailFragment = AskStoryDetailFragment()

                //Use replace() to replace an existing fragment in a container
                // with an instance of a new fragment class that you provide.
                transaction.replace(com.ashandroid.showcase.hnews.R.id.hacker_news, askStoryDetailFragment)

                transaction.addToBackStack(askStoryDetailFragment::class.simpleName)

                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)

                transaction.commit()
            }
        })

        viewModel.urlOpen.observe(this, {
            val urlFragment = UrlFragment()
            // consider using Java coding conventions (upper first char class names!!!)
            val transaction = this.supportFragmentManager.beginTransaction()

            // Replace whatever is in the fragment_container(usually a frame layout) view with this fragment,
            // and add the transaction to the back stack
            transaction.replace(com.ashandroid.showcase.hnews.R.id.hacker_news, urlFragment)

            transaction.addToBackStack(urlFragment::class.simpleName)
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            // Commit the transaction
            transaction.commit()
        })
        super.onResume()
    }

    override fun onBackPressed() {
        // backStackEntryCount means number of fragments in the back stack
        val count = supportFragmentManager.backStackEntryCount
        if (count >= 1) {
            // popBackStack() - Pop the top state off the back stack. (or)
                // Remove the top of the fragment in the stack based on count
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}

