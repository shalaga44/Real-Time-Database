package com.shalaga44.chatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shalaga44.chatapp.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        attachViewsOnClickListener()

    }

    private fun attachViewsOnClickListener() {
        binding.confirm.setOnClickListener {

        }
    }
}