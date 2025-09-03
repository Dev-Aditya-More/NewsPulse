package com.example.basicnewsapp.data

import com.example.basicnewsapp.data.remote.NewsApi
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val api: NewsApi
) {
    suspend fun getTopHeadlines(apiKey: String) = api.getTopHeadlines(apiKey = apiKey)
}
