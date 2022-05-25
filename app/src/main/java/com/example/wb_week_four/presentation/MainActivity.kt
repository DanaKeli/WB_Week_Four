package com.example.wb_week_four.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wb_week_four.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.statusBarColor = resources.getColor(R.color.blue)
    }
}