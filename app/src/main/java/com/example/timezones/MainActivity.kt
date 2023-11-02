package com.example.timezones2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.timezones.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startGameButton: Button = findViewById(R.id.startGameButton)

        startGameButton.setOnClickListener {
            val intent = Intent(this, PreGameActivity::class.java)
            startActivity(intent)
        }
    }
}