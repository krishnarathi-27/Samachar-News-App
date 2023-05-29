package com.example.samachar.api

import com.example.samachar.models.NewsResponse
import com.example.samachar.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

//this interface is used to represent a singl request that we can execute from code
interface NewsAPI {

    //get is the type of request as we need to get the data from the api
    //the url is part of url after the base url that we need to get from api

    @GET("v2/top-headlines")
    //as this is a network call function so we need to execute the function asynchronously for this the best thng is couroutine
    //to make the function able to use in coroutine we make a suspend function

    suspend fun getBreakingNews(
        //to get breaking news we need to add paramenters to this function
        //and if the parameter is part of request that if it is a request parameter we need to annotate that parameter with add query

        @Query("country")//name of parameter
        countryCode: String = "us",//variable value of parameter
        @Query("page")//to paginate the request as we donot need all the data at once and for next article we request for next page
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey:String = API_KEY //constant variable in utils constant class
    ): Response<NewsResponse> //need to return the response object and NewsResponse was the response object generated from convertor plugin

    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey:String = API_KEY
    ): Response<NewsResponse>
}

/* first step is to create a response object
whenever we make a request with retrofit we get the answer as json string (Json is a way to convert complex object in simple string

so we need to create kotlin object that represent the answer we get from news api for this we used a kotlin plugin i.e kotlin sata class file from json
open ctrl+alt+s
 */

/* DAO stands for data access object
 it is an interface just like our newsapi interface in newsapi we define functions how we can access aour api how we can make api request

 DAO is also the same but for our local database here we will define the functions that will access the local database
 */