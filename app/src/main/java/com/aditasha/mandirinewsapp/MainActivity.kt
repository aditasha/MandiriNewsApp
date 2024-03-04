package com.aditasha.mandirinewsapp

import com.aditasha.mandirinewsapp.adapter.HeaderAdapter
import com.aditasha.mandirinewsapp.adapter.NewsAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aditasha.mandirinewsapp.databinding.ActivityMainBinding
import com.aditasha.mandirinewsapp.network.CallAPI

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Binding & inflate
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Set adapter
        val headerAdapter = HeaderAdapter()
        val newsAdapter = NewsAdapter()
        binding.header.adapter = headerAdapter
        binding.news.adapter = newsAdapter

        // API call
        val api = CallAPI.getInstance()
        api.callHeadlines(headerAdapter)
        api.callNews(newsAdapter)
    }
}