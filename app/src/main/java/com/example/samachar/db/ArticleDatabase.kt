package com.example.samachar.db

import android.content.Context
import androidx.room.*
import com.example.samachar.models.Article

//database class for room always need to be abstract
@Database(
    entities = [Article::class],
    version = 1       //version is used to update the room database so whenever we change the databsse we need to update the version
)

@TypeConverters(Convertors :: class)
abstract class ArticleDatabase : RoomDatabase(){

    abstract fun getArticleDao() : ArticleDAO

    //volatile means other threads can immediately see when a thread changes this instance
    companion object{  //companion object creates the actual database

        @Volatile
        private var instance : ArticleDatabase? = null //singleton instance of database
        private val LOCK = Any() //to synchronise to ensure that there is only a single instannce of database at once

        //this is called when ever we create the instance of our database if instance is not null it will return the current isnatnce o/w it will create database with the instance
        operator fun invoke(context : Context) = instance ?: synchronized(LOCK){
            //this means the thing inside this block of code cant be accessed by other threads at same time
            //this makes sure that there is no other thread that sets this instance while we aready set it

            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
            ArticleDatabase::class.java,
                "article_db.db"
            ).build()
    }
}