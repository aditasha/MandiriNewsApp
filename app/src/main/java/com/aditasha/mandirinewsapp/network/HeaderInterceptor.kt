package com.aditasha.mandirinewsapp.network

import com.aditasha.mandirinewsapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor private constructor() : Interceptor {
    companion object {
        @Volatile
        private var instance: HeaderInterceptor? = null

        fun getInstance(): HeaderInterceptor {
            if (instance == null) {
                synchronized(this) {
                    if (instance == null) {
                        instance = HeaderInterceptor()
                    }
                }
            }
            return instance!!
        }
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("x-api-key", BuildConfig.NEWS_API_KEY)
            .build()
        return chain.proceed(request)
    }
}