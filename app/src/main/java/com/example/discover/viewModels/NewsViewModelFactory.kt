package com.example.discover.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.discover.repository.NewsRepository

class NewsViewModelFactory(private val newsRepository: NewsRepository, private val q: String, private val publishedAt: String) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(newsRepository, q, publishedAt) as T
    }
}