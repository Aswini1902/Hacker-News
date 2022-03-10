package com.ashandroid.showcase.hnews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashandroid.showcase.hnews.api.StoriesApi
import com.ashandroid.showcase.hnews.model.Stories
import kotlinx.coroutines.launch


enum class TopStoriesApiStatus {LOADING, ERROR, DONE}
class StoriesViewModel : ViewModel() {

    private val _status = MutableLiveData<TopStoriesApiStatus>()
    val status: LiveData<TopStoriesApiStatus> = _status

    private val _topStoriesList = MutableLiveData<List<Stories>>()
    val topStoriesList: LiveData<List<Stories>> = _topStoriesList

    private val _jobsList = MutableLiveData<List<Stories>>()
    val jobsList: LiveData<List<Stories>> = _jobsList

    private val _StoriesData = MutableLiveData<Stories>()
    val StoriesData: LiveData<Stories> = _StoriesData

    private val _openDetailsView = MutableLiveData<Boolean>()
    var showDetail : LiveData<Boolean> = _openDetailsView



    fun getTopStories() {
        viewModelScope.launch {
            _status.value = TopStoriesApiStatus.LOADING
            try {
                val idList  = StoriesApi.retrofitService.getTopStoriesIdList()
                var list = mutableListOf<Stories>()
                for (i in idList.subList(0,10)){
                    _StoriesData.value = StoriesApi.retrofitService.getTopStories(i.toString())
                    list.add(_StoriesData.value!!)
                }
                _topStoriesList.value = list

                _status.value = TopStoriesApiStatus.DONE
            } catch (e: Exception){
                _status.value = TopStoriesApiStatus.ERROR
            }
        }
    }

    // Create a function for getting top story data when click an item
    // Will pass the function inside the StoryListener in TopStoriesFragment to get data
    fun onTopStoriesClicked(story: Stories) {
        _StoriesData.value = story
        _openDetailsView.value = true
    }

    fun getJobStoriesList() {
        viewModelScope.launch {
            _status.value = TopStoriesApiStatus.LOADING
            try {
                val idList  = StoriesApi.retrofitService.getJobStoriesIdList()
                var list = mutableListOf<Stories>()
                for (i in idList.subList(0,10)){
                    val job = StoriesApi.retrofitService.getJobStories(i.toString())
                    list.add(job)
                }
                _jobsList.value = list

                _status.value = TopStoriesApiStatus.DONE
            } catch (e: Exception){
                _status.value = TopStoriesApiStatus.ERROR
            }
        }
    }
}