package com.shalaga44.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.shalaga44.chatapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        attachViewsOnClickListener()
        disableLoginButtonByText()



    }

    private fun disableLoginButtonByText() {
        val loginTextWatcher = object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //this line disable log in button if any of the edit texts empty
                _binding.login.isEnabled=!_binding.usernameInput.editText?.text?.trim().isNullOrEmpty() &&!_binding.passwordInput.editText?.text?.trim().isNullOrEmpty()
            }
        }
        _binding.usernameInput.editText?.addTextChangedListener(loginTextWatcher)
        _binding.passwordInput.editText?.addTextChangedListener(loginTextWatcher)

    }

    private fun attachViewsOnClickListener() {
        _binding.signup.setOnClickListener {
            startActivity(
                Intent(this,
                    SignupActivity::class.java)) }
    }
}