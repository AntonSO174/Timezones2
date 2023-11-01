package com.example.timezones2
import android.util.Log
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.timezones.R



class PreGameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pre_game)


        Handler(Looper.getMainLooper()).postDelayed({
            startGameActivity()
        }, 10000)

        val nextButton: Button = findViewById(R.id.next_button)
        nextButton.setOnClickListener {
            Log.d("MyApp", "Button Clicked")
            startGameActivity()
        }
    }

    private fun startGameActivity() {
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)
    }

}
