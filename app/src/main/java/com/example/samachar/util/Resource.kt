package com.example.samachar.util

//this class is recommended by google to wrap around your network responses
//generic class useful to differentiate between successful and error netwrok response
//sealed class is a type of abstract class which allows which classes to inherit from that resource class
//in this class we will define classes and only those classes would be allowed to inherit from resource
sealed class Resource<T>(
    val data:T? = null,
    val message:String? = null
) {

    //success class is not nullable as we are sure that if response is successful then we will have some data with us
    class Success<T>(data : T) : Resource<T>(data)
    class Error<T>(message: String,data:T?=null) : Resource<T>(data, message)
    class Loading<T> : Resource<T>()
}