package com.aditasha.mandirinewsapp.network


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val baseUrl = "https://newsapi.org/v2/"
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC);
    private val client = OkHttpClient.Builder()
        .addInterceptor(HeaderInterceptor.getInstance())
        .addInterceptor(logging)
        .build()
}

object ApiClient {
    val apiService: ApiInterface by lazy {
        RetrofitClient.retrofit.create(ApiInterface::class.java)
    }
}
