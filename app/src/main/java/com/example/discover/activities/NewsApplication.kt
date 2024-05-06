package com.example.discover.activities

import android.app.Application
import com.example.discover.repository.NewsRepository
import com.example.discover.retrofit.ApiInterface
import com.example.discover.retrofit.RetrofitHelper
import com.example.discover.viewModels.NewsViewModel

class NewsApplication : Application() {

    lateinit var apiInterface: ApiInterface
    lateinit var newsRepository: NewsRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        apiInterface = RetrofitHelper.getInstance().create(ApiInterface::class.java)
        newsRepository = NewsRepository(apiInterface)
    }
}