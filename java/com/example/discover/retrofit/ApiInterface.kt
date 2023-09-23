package com.example.discover.retrofit

import com.example.discover.models.News
import com.example.discover.utils.Constants.Companion.API_KEY
import com.example.discover.utils.Constants.Companion.BASE_URL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("apiKey") apiKey: String = API_KEY
    ) : Response<News>

    @GET("everything")
    suspend fun getAllNews(
        @Query("q") q: String,
        @Query("sortBy") sortBy: String,
        @Query("apiKey") apiKey: String = API_KEY
    ) : Response<News>
}