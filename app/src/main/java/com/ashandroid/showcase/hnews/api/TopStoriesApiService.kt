package com.ashandroid.showcase.hnews.api

import com.ashandroid.showcase.hnews.model.Stories
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val URL = "https://hacker-news.firebaseio.com/v0/"

// Add a Moshi builder to build and create a Moshi object.
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
// Add a Retrofit builder to build and create a Retrofit object.

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(URL)
    .build()

interface StoriesApiService {
   // TOP STORIES
    @GET("topstories.json")
    suspend fun getTopStoriesIdList() : List<Int>
    @GET("item/{id}.json")
    suspend fun getTopStories(@Path("id") id: String) : Stories

    // JOB STORIES
    @GET("jobstories.json")
    suspend fun getJobStoriesIdList() : List<Int>
    @GET("item/{id}.json")
    suspend fun getJobStories(@Path("id") id: String) : Stories

    // ASK STORIES
    @GET("askstories.json")
    suspend fun getAskStoriesIdList() : List<Int>
    @GET("item/{id}.json")
    suspend fun getAskStories(@Path("id") id: String) : Stories

}

// define a public object called StoriesApi to initialize the Retrofit service.
object StoriesApi {

    //  add a lazily initialized retrofit object property
    // Initialize the retrofitService variable using the retrofit.create() method
    val retrofitService : StoriesApiService by lazy {
        retrofit.create(StoriesApiService::class.java)
    }
}