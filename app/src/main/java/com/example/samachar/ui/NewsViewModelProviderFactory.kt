package com.example.samachar.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.samachar.repository.NewsRepository

class NewsViewModelProviderFactory(
     val newsRepository: NewsRepository
) : ViewModelProvider.AndroidViewModelFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        val newsViewModel = NewsViewModel()

        newsViewModel.init(newsRepository)
        return newsViewModel as T

    }
}