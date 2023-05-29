package com.example.samachar.repository

import com.example.samachar.api.RetrofitInstance
import com.example.samachar.db.ArticleDatabase
import com.example.samachar.models.Article

//to get data from database and from retrofit
class NewsRepository(
    val db: ArticleDatabase
) {

    //this function directly queries our api for the breaking news
    suspend fun getBreakingNews(countryCode : String,pageNumber:Int) =
        RetrofitInstance.api.getBreakingNews(countryCode,pageNumber)

    suspend fun searchNews(searchQuery: String,pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery,pageNumber)

    suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)

    fun getSavedNews() = db.getArticleDao().getAllArticle()

    suspend fun deletArticle(article: Article) = db.getArticleDao().deleteArticle(article)

}