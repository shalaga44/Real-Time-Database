package com.shalaga44.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shalaga44.chatapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        attachViewsOnClickListener()

    }

    private fun attachViewsOnClickListener() {
        binding.signup.setOnClickListener {
            startActivity(
                Intent(this,
                    SignupActivity::class.java)) }
    }
}