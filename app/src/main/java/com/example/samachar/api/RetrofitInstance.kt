package com.example.samachar.api

import com.example.samachar.util.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//retrofit singleton class that enables us to mak request from everyhwere in our code
class RetrofitInstance {

    companion object{
        private val retrofit by lazy{       //lazy means we have initialised here only once what we have put in the curly braces

            //we attach interceptor in retrofit object for what request we are actually making and what responses are
            val logging = HttpLoggingInterceptor()
            //to see the body of the actual response
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            //network client
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            //this is what we are returning in the lazy block
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                    //used to determine how the responses are actually interpreted and converted to kotlin objects
                    //gson convertor is the google implementation of the jsoon converting
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        //now we can get our api instance from retrofit builder
        val api by lazy {
            //here we would return the api interface
            retrofit.create(NewsAPI::class.java)
        }
        //this is our actual api object that we would be able to use from everywhere to make our actual netwrok call
    }
}