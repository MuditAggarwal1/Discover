package com.example.discover.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.discover.models.News
import com.example.discover.retrofit.ApiInterface

class NewsRepository(private val apiInterface: ApiInterface) {

    private val isLoading = MutableLiveData<Boolean>()
    private val newsTopHeadlinesMutableLiveData = MutableLiveData<News>()
    val newsTopHeadlinesLiveData: LiveData<News>
    get() = newsTopHeadlinesMutableLiveData

    private val newsAllMutableLiveData = MutableLiveData<News>()
    val newsAllLiveData: LiveData<News>
    get() = newsAllMutableLiveData

    suspend fun getTopHeadlines(country: String, category: String) {
        val topHeadlinesData = apiInterface.getTopHeadlines(country, category)

        if(topHeadlinesData.body() != null) {
            newsTopHeadlinesMutableLiveData.postValue(topHeadlinesData.body())
        }
    }

    suspend fun getAllNews(q: String, sortBy: String) {

        isLoading.postValue(true)
        val allNews = apiInterface.getAllNews(q, sortBy)

        if(allNews.body() != null) {
            newsAllMutableLiveData.postValue(allNews.body())
            isLoading.postValue(false)
        }
    }

    fun getIsLoading(): LiveData<Boolean> {
        return isLoading
    }
}