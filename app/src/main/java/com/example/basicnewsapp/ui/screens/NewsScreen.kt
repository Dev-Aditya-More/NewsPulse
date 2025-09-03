package com.example.basicnewsapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.basicnewsapp.ui.viewmodels.NewsViewModel
import com.example.basicnewsapp.utils.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(
    viewModel: NewsViewModel = hiltViewModel()
) {
    val articles by viewModel.articles.collectAsState()

    // Call once
    LaunchedEffect(Unit) {
        viewModel.loadNews(Constants.API_KEY)
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Basic News App") })
        }
    ) { padding ->
        LazyColumn(contentPadding = padding) {
            items(articles) { article ->
                NewsItem(article)
            }
        }
    }
}

@Composable
fun NewsItem(article: com.example.basicnewsapp.data.remote.Article) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        article.urlToImage?.let {
            Image(
                painter = rememberAsyncImagePainter(it),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )
        }
        Text(text = article.title ?: "", style = MaterialTheme.typography.titleMedium)
        Text(text = article.description ?: "", style = MaterialTheme.typography.bodyMedium)
    }
}