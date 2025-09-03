package com.example.basicnewsapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basicnewsapp.data.NewsRepository
import com.example.basicnewsapp.data.remote.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles = _articles.asStateFlow()

    fun loadNews(apiKey: String) {
        viewModelScope.launch {
            try {
                val response = repository.getTopHeadlines(apiKey)
                _articles.value = response.articles
                println("✅ Got ${response.articles.size} articles")
            } catch (e: Exception) {
                e.printStackTrace()
                println("❌ Error: ${e.message}")
            }
        }
    }
}
