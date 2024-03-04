package com.aditasha.mandirinewsapp

import HeaderAdapter
import NewsAdapter
import android.util.Log
import com.aditasha.mandirinewsapp.model.News
import com.aditasha.mandirinewsapp.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CallAPI private constructor() {
    companion object {
        @Volatile
        private var instance: CallAPI? = null

        fun getInstance(): CallAPI {
            if (instance == null) {
                synchronized(this) {
                    if (instance == null) {
                        instance = CallAPI()
                    }
                }
            }
            return instance!!
        }
    }

    fun callHeadlines(adapter: HeaderAdapter) {
        val getHeadlines = ApiClient.apiService.getHeadlines(
            mapOf(
                Pair("country", "us")
            )
        )

        getHeadlines.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                if (response.isSuccessful) {
                    val post = response.body()
                    val articles = post?.articles
                    articles?.get(0)?.let {
                        adapter.setData(it)
                    }
                } else {
                    Log.e("headlines error", response.toString())
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.e("headlines failure", t.message.toString())
            }
        })
    }

    fun callNews(adapter: NewsAdapter) {
        val getNews = ApiClient.apiService.getNews(
            mapOf(
                Pair("q", "bank")
            )
        )

        getNews.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                if (response.isSuccessful) {
                    val post = response.body()
                    val articles = post?.articles
                    articles?.let {
                        adapter.setData(it)
                    }
                } else {
                    Log.e("articles error", response.toString())
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.e("articles failure", t.message.toString())
            }
        })
    }
}
