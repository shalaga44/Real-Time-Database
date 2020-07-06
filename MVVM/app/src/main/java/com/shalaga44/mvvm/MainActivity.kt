package com.shalaga44.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shalaga44.mvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}