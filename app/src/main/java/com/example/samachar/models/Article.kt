package com.example.samachar.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

//to save article in our database annotate with entity this would tell android studio that this article class is a table in the database


@Entity(
    tableName = "article"
)
data class Article(
    @PrimaryKey(autoGenerate = true) //this will automatically increment the id
    var id : Int? = null,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: Source?,  //room can only handle primitive data type and cant handle our own created custom source class
    //so we need to create a type convertor that converts our source class to any primitive data type and if we have string we need to tell how to convert the string to the sorce class
    val title: String?,
    val url: String?,
    val urlToImage: String?
) : Serializable

/*we inherited serializable coz :-
we want to open the articles in the webview so to save them later for that we nned to somehow transfer the article click on the
article fragment fow which we need saveargs from navigation component library to enable us to pass our arguments to  transitions from
1 fragment to another and coz
article class is not a primitive one it is complex data type so we ness to mark this class serializable which tells kotlin that we
want to be able to pass this class b/w several fragments with the navigation component.

*/