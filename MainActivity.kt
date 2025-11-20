package com.example.lab5

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.e("MainActivity","onCreate")

        val viewPager2 = findViewById<ViewPager2>(R.id.viewPager2)

        // 錯誤修復點：使用 val 賦值，並傳入正確的參數 (只需要 this)
        val adapter = ViewPagerAdapter(this)

        viewPager2.adapter = adapter

        viewPager2.offscreenPageLimit = 1
    }

    // 4. 只保留核心生命週期 Log，移除 onRestart/onPause/onStop/onDestroy
    override fun onStart() {
        super.onStart()
        Log.e("MainActivity", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("MainActivity", "onResume")
    }
}