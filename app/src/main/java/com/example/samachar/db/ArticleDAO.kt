package com.example.samachar.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.samachar.models.Article

@Dao    //annotate with dao so that room actually knows that this is the interface
interface ArticleDAO {


    //onconflict strategy determines what happens if the article we insert is already present in db we will here replace it
    //all these functions would be suspend function so wee use coroutines for that
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article): Long
    //return the id that is inserted

    //pass sql query that should select all articles that this function should return

    /* here it would not be a suspend function as it would return live data that would not work with suspend function
    live data is basically android architectural component that enable fragment to subscribe changes and whenever the data changes
    the live data will notify all its observers so that the fragments can update the recylerview in that case
     live data is important in case of device rotation as on rotation the activity is recreated so view model is not updated when we use live data
    then our recycler view immediately gets latest updated data */

    @Query("SELECT * FROM article")
    fun getAllArticle(): LiveData<List<Article>> //it will notify all its observer through live data to update changes

    @Delete
    suspend fun deleteArticle(article: Article)
}