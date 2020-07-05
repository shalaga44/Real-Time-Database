package com.shalaga44.chatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import androidx.core.widget.addTextChangedListener
import com.shalaga44.chatapp.databinding.ActivitySignupBinding
import java.util.*

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        attachViewsOnClickListener()
        attachTextsChangedListener()

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

    private fun attachTextsChangedListener() {
        var emailWritten = false
        binding.usernameInput.editText?.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                validateUserName()
            }
        })
        binding.emailInput.editText?.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                if(emailWritten) {
                    validateEmail()
                }
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        binding.passwordInput.editText?.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(p0: Editable?) {

            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                emailWritten = true
                validateEmail()
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                validatePassword()
            }
        })
    }
}