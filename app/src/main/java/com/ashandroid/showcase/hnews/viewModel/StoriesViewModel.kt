package com.ashandroid.showcase.hnews

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashandroid.showcase.hnews.api.StoriesApi
import com.ashandroid.showcase.hnews.model.Stories
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

enum class StoriesApiStatus {LOADING, ERROR, DONE}
class StoriesViewModel : ViewModel() {

    private val _status = MutableLiveData<StoriesApiStatus>()
    val status: LiveData<StoriesApiStatus> = _status

    // Create a variable to get TopStories List
    private val _topStoriesList = MutableLiveData<List<Stories>>()
    val topStoriesList: LiveData<List<Stories>> = _topStoriesList

    // Create a variable to get JobStories List
    private val _jobsList = MutableLiveData<List<Stories>>()
    val jobsList: LiveData<List<Stories>> = _jobsList

    // Create a variable to get AskStories List
    private val _askList = MutableLiveData<List<Stories>>()
    val askList: LiveData<List<Stories>> = _askList

    // Create a variable to get Stories of data
    private val _StoriesData = MutableLiveData<Stories>()
    val StoriesData: LiveData<Stories> = _StoriesData

    //_openDetailsView for topStory details page only
    private val _openDetailsView = MutableLiveData<Boolean>()
    var showDetail : LiveData<Boolean> = _openDetailsView

    //_openJobsView for job details page only
    private val _openJobsView = MutableLiveData<Boolean>()
    var showJobDetail : LiveData<Boolean> = _openJobsView

    //_openQAView for QA details page only
    private val _openQAView = MutableLiveData<Boolean>()
    var showQADetail : LiveData<Boolean> = _openQAView

    private val _urlOpen = MutableLiveData<Boolean>()
    var urlOpen : LiveData<Boolean> = _urlOpen

    private val _showProgressTopStories = MutableLiveData<Boolean>()
    var showProgressTopStories: LiveData<Boolean> = _showProgressTopStories

    private val _showProgressJobStories = MutableLiveData<Boolean>()
    var showProgressJobStories: LiveData<Boolean> = _showProgressJobStories

    private val _showProgressQA = MutableLiveData<Boolean>()
    var showProgressQA: LiveData<Boolean> = _showProgressQA




    fun getTopStories(n:Int = 0) {
        viewModelScope.launch {

            _status.value = StoriesApiStatus.LOADING
            try {
                val oldEntryTopStory = (_topStoriesList.value?.size ?: 0) > 0
                if(n==0 && oldEntryTopStory){
                    //dont load more until scroll end
                    _status.value = StoriesApiStatus.DONE
                    return@launch
                }
                _showProgressTopStories.value = true
                var idList  = StoriesApi.retrofitService.getTopStoriesIdList()
                idList = idList.toSet().toList()
                var list = mutableListOf<Stories>()
                val start = n * 10
                val end = start + 10
                for (i in idList.subList(start, end)){
                    val story = StoriesApi.retrofitService.getTopStories(i.toString())
                    list.add(story)
                }

                if (oldEntryTopStory || n!=0){
                    val oldList = _topStoriesList.value !!
                    val oldMutableList = mutableListOf<Stories>()
                    for(item in oldList){
                        oldMutableList.add(item)
                    }
                    oldMutableList.addAll(list)
                    _topStoriesList.value = oldMutableList
                } else{
                    _topStoriesList.value = list
                }


                _status.value = StoriesApiStatus.DONE
                _showProgressTopStories.value = false
            } catch (e: Exception){
                _status.value = StoriesApiStatus.ERROR
                _showProgressTopStories.value = false
            }
        }
    }

    fun getJobStories(n:Int = 0) {
        viewModelScope.launch {

            val oldEntry = (_jobsList.value?.size ?: 0) > 0
            if(n==0 && oldEntry){
                //dont load more until scroll end
                _status.value = StoriesApiStatus.DONE
                return@launch
            }

            _showProgressJobStories.value = true
            _status.value = StoriesApiStatus.LOADING
            try {
                var idList  = StoriesApi.retrofitService.getJobStoriesIdList()
                idList = idList.toSet().toList()
                var list = mutableListOf<Stories>()
                val start = n*10
                val end = start + 10
                for (i in idList.subList(start,end)){
                    val job = StoriesApi.retrofitService.getJobStories(i.toString())
                    list.add(job)
                }

                if (oldEntry || n!=0){
                    val oldList = _jobsList.value !!
                    val oldMutableList = mutableListOf<Stories>()
                    for(item in oldList){
                        oldMutableList.add(item)
                    }
                    oldMutableList.addAll(list)
                    _jobsList.value = oldMutableList.sortedBy { it.time }.reversed()
                }
                else{
                    _jobsList.value = list.sortedBy { it.time }.reversed()
                }



                Log.d("scroll", "${_jobsList.value}")

                _status.value = StoriesApiStatus.DONE
                _showProgressJobStories.value = false
            } catch (e: Exception){
                _status.value = StoriesApiStatus.ERROR
                _showProgressJobStories.value = false
            }
        }
    }

    fun getAskStories(n:Int = 0) {
        viewModelScope.launch {

            val oldEntry = (_askList.value?.size ?: 0) > 0
            if(n==0 && oldEntry){
                //dont load more until scroll end
                _status.value = StoriesApiStatus.DONE
                return@launch
            }

            _status.value = StoriesApiStatus.LOADING
            _showProgressQA.value = true
            try {
                val idList  = StoriesApi.retrofitService.getAskStoriesIdList()
                var list = mutableListOf<Stories>()
                val start = n*10
                val end = start + 10


                for (i in idList.subList(start,end)){
                    val job = StoriesApi.retrofitService.getAskStories(i.toString())
                    list.add(job)
                }

                if (oldEntry || n!=0){
                    val oldList = _askList.value !!
                    val oldMutableList = mutableListOf<Stories>()
                    for(item in oldList){
                        oldMutableList.add(item)
                    }
                    oldMutableList.addAll(list)
                    _askList.value = oldMutableList.sortedBy { it.time }.reversed()
                }
                else{
                    _askList.value = list.sortedBy { it.time }.reversed()
                }


                _askList.value = list

                _status.value = StoriesApiStatus.DONE
                _showProgressQA.value = false
            } catch (e: Exception){
                _status.value = StoriesApiStatus.ERROR
                _showProgressQA.value = false
            }
        }
    }
    // Create a function for getting top story data when click an item
    // Will pass the function inside the StoryListener in TopStoriesFragment to get data
    fun onTopStoriesClicked(story: Stories) {
        _StoriesData.value = story
        _openDetailsView.value = true
    }

    fun onQAClicked(story: Stories) {
        _StoriesData.value = story
        _openQAView.value = true
    }

    fun onUrlClicked(){
        _urlOpen.value = true
    }

    fun getDateString(unixTimeSeconds : Long): String?{
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val unixTimeMilliSeconds = unixTimeSeconds*1000
        return sdf.format(Date(unixTimeMilliSeconds))
    }


}