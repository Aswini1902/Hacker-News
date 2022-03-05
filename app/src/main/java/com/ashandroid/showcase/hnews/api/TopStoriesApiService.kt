package com.ashandroid.showcase.hnews.api

import com.ashandroid.showcase.hnews.model.Stories
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.converter.scalars.ScalarsConverterFactory

//private const val URL = "https://hacker-news.firebaseio.com/v0/item/"
private const val URL = "https://hacker-news.firebaseio.com/v0/"


// Add a Retrofit builder to build and create a Retrofit object.


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(URL)
    .build()

interface TopStoriesApiService {
    @GET("topstories.json")
    suspend fun getTopStoriesIdList() : List<Int>
    //@GET("30496842.json")
    //suspend fun getTopStoriesList() : Stories
    //@GET("item/{item-id}.json?print=pretty")

}

// define a public object called MarsApi to initialize the Retrofit service.
object TopStoriesApi {

    //  add a lazily initialized retrofit object property
    // Initialize the retrofitService variable using the retrofit.create() method
    val retrofitService : TopStoriesApiService by lazy {
        retrofit.create(TopStoriesApiService::class.java)
    }
}