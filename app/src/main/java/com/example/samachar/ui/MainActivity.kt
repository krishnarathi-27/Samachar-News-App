package com.example.samachar.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.samachar.R
import java.util.*
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivity()
    }
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun mainActivity(){

        if(!isDestroyed){

            val intent = Intent(this, NewsActivity::class.java)
            val tmtask = timerTask{
                if(!isDestroyed){
                    startActivity(intent)
                    finish()
                }
            }
            val timer = Timer()
            timer.schedule(tmtask,3000)
        }
    }
}