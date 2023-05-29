package com.example.samachar.util

class Constants {
    //using companion object we dont need to create instance of the class
    companion object{
        const val API_KEY = "df9554319d27455a8d44678b3e72d740"
        const val BASE_URL = "https://newsapi.org"
        const val SEARCH_NEWS_TIME_DELAY = 500L
        const val QUERY_PAGE_SIZE = 20 //as our response contains 20 items

    }
}