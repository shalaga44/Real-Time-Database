package com.shalaga44.chatapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(toolbar)
        initViews()

    }

    private fun initViews() {
        findViewById<Button>(R.id.btn_signup).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
