package com.shalaga44.chatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import com.shalaga44.chatapp.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    private lateinit var _binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        attachViewsOnClickListener()
        attachTextsChangedListener()

    }

    private fun validatePassword(): Boolean {
        val passwordInput = _binding.passwordInput.editText?.text?.trim()
        return when {
            passwordInput.isNullOrEmpty() -> {
                _binding.passwordInput.error = "Field can't be empty"
                false
            }
            passwordInput.length < 7 -> {
                _binding.passwordInput.error = "Password is weak"
                false
            }
            else -> {
                _binding.passwordInput.error = ""
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
        val emailInput = _binding.emailInput.editText?.text?.trim()
        if (emailInput.isNullOrEmpty()) {
            _binding.emailInput.error = "Field can't be empty"
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            _binding.emailInput.error = "Not valid email"
            return false
        } else {
            _binding.emailInput.error = ""
            return true
        }


    }


    private fun validateUserName(): Boolean {
        val usernamelInput = _binding.usernameInput.editText?.text?.trim()
        return when {
            usernamelInput.isNullOrEmpty() -> {
                _binding.usernameInput.error = "Field can't be empty"
                false
            }
            usernamelInput.length > 15 -> {
                _binding.usernameInput.error = "username too long"
                false
            }
            else -> {
                _binding.usernameInput.error = ""
                true
            }
        }

    }

    private fun attachViewsOnClickListener() {
        _binding.confirm.setOnClickListener {
            validateInput()
        }
    }

    private fun attachTextsChangedListener() {
        var emailWritten = false
        _binding.usernameInput.editText?.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                validateUserName()
            }
        })
        _binding.emailInput.editText?.addTextChangedListener(object:TextWatcher{
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
        _binding.passwordInput.editText?.addTextChangedListener(object:TextWatcher{
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