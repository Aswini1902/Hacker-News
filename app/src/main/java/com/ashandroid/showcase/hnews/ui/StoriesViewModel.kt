package com.ashandroid.showcase.hnews

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashandroid.showcase.hnews.api.TopStoriesApi
import com.ashandroid.showcase.hnews.model.Stories
import kotlinx.coroutines.launch


enum class TopStoriesApiStatus {LOADING, ERROR, DONE}
class StoriesViewModel : ViewModel() {

    private val _status = MutableLiveData<TopStoriesApiStatus>()
    val status: LiveData<TopStoriesApiStatus> = _status

    private val _listTopStories = MutableLiveData<List<Int>>()
    val listTopStories: LiveData<List<Int>> = _listTopStories

    private val _topStories = MutableLiveData<Int>()
    val topStories: LiveData<Int> = _topStories

    fun getTopStoriesList() {
        viewModelScope.launch {
            _status.value = TopStoriesApiStatus.LOADING
            try {
                //_listTopStories.value = TopStoriesApi.retrofitService.getTopStoriesList()
                _listTopStories.value = TopStoriesApi.retrofitService.getTopStoriesIdList()
                Log.d("list", " list ${listTopStories}")
                _status.value = TopStoriesApiStatus.DONE
            } catch (e: Exception){
                _status.value = TopStoriesApiStatus.ERROR
                //_listTopStories.value = Stories()
            }
        }
    }
    // Create a function for getting data item when click an item
    fun onTopStoriesClicked(story: Int) {
        _topStories.value = story
    }
}