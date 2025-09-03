package com.example.basicnewsapp.data.remote

import com.example.basicnewsapp.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "in",
        @Query("apiKey") apiKey: String = Constants.API_KEY
    ): NewsResponse
}