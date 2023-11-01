package com.example.timezones2
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.timezones.R

class ScoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val score = intent.getIntExtra("SCORE", 0)
        val scoreTextView: TextView = findViewById(R.id.score_text_view)

        // Форматируем строку для отображения счета, можно добавить дополнительный текст или стиль
        scoreTextView.text = String.format("Ваш итоговый счет: %d", score)
    }
}
