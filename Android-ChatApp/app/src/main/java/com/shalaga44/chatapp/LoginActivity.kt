package com.shalaga44.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.content.ContextCompat.startActivity
import com.shalaga44.chatapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
                binding.login.isEnabled=!binding.usernameInput.editText?.text?.trim().isNullOrEmpty() &&!binding.passwordInput.editText?.text?.trim().isNullOrEmpty()
            }
        }
        binding.usernameInput.editText?.addTextChangedListener(loginTextWatcher)
        binding.passwordInput.editText?.addTextChangedListener(loginTextWatcher)

    }

    private fun attachViewsOnClickListener() {
        binding.signup.setOnClickListener {
            startActivity(
                Intent(this,
                    SignupActivity::class.java)) }
    }
}