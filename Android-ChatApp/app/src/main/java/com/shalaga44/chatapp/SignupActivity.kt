package com.shalaga44.chatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import com.shalaga44.chatapp.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        attachViewsOnClickListener()


    }

    private fun validatePassword(): Boolean {
        val passwordInput = binding.passwordInput.editText?.text?.trim()
        return when {
            passwordInput.isNullOrEmpty() -> {
                binding.passwordInput.error = "Field can't be empty"
                false
            }
            passwordInput.length < 7 -> {
                binding.passwordInput.error = "Password is weak"
                false
            }
            else -> {
                binding.passwordInput.error = ""
                true
            }
        }

    }

    private fun validateInput(): Boolean {
        validateUserName()
        validateEmail()
        validatePassword()


        return false
    }


    private fun validateEmail(): Boolean {
        val emailInput = binding.emailInput.editText?.text?.trim()
        if (emailInput.isNullOrEmpty()) {
            binding.emailInput.error = "Field can't be empty"
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            binding.emailInput.error = "Not valid email"
            return false
        } else {
            binding.emailInput.error = ""
            return true
        }


    }


    private fun validateUserName(): Boolean {
        val usernamelInput = binding.usernameInput.editText?.text?.trim()
        return when {
            usernamelInput.isNullOrEmpty() -> {
                binding.usernameInput.error = "Field can't be empty"
                false
            }
            usernamelInput.length > 15 -> {
                binding.usernameInput.error = "username too long"
                false
            }
            else -> {
                binding.usernameInput.error = ""
                true
            }
        }

    }

    private fun attachViewsOnClickListener() {
        binding.confirm.setOnClickListener {
            validateInput()
        }
    }
}