package com.shalaga44.chatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputLayout


class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        var usernameTextInput: TextInputLayout = findViewById(R.id.username)
        var emailTextInput: TextInputLayout = findViewById(R.id.email)
        var passwordTextInput: TextInputLayout = findViewById(R.id.password)
        var signupButton: Button = findViewById(R.id.btnSignup)

    }


}
