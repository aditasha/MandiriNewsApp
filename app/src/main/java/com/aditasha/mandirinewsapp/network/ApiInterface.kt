package com.aditasha.mandirinewsapp.network

import com.aditasha.mandirinewsapp.model.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiInterface {
    @GET("top-headlines")
    fun getHeadlines(
        @QueryMap options: Map<String, String>
    ): Call<News>

    @GET("everything")
    fun getNews(
        @QueryMap options: Map<String, String>
    ): Call<News>
}