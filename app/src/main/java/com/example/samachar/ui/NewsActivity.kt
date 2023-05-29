package com.example.samachar.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.samachar.R
import com.example.samachar.db.ArticleDatabase
import com.example.samachar.repository.NewsRepository
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main2.*


class NewsActivity : AppCompatActivity() {

    lateinit var viewModel: NewsViewModel
    lateinit var newsNavHostFragment: Fragment
    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        newsNavHostFragment = supportFragmentManager.findFragmentById(R.id.newsNavHostFragment)!!
        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[NewsViewModel::class.java]
        viewModel.init(newsRepository)
//        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(
//                NewsViewModel::class.java
//            )
        bottomNavigationView.setupWithNavController(newsNavHostFragment.findNavController())
    }
}