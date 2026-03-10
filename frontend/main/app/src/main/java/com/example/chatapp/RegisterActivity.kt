package com.example.chatapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val backButton = findViewById<Button>(R.id.btnBack)
        val successButton = findViewById<Button>(R.id.btnSuccess)

        backButton.setOnClickListener {
            finish()
        }

        successButton.setOnClickListener {
            val intent = Intent(this, ChatsActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}