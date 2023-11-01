package com.example.timezones2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.timezones.R

class GameActivity : AppCompatActivity() {

  
    private var score: Int = 0

    private var selectedClock: ImageView? = null
    private var selectedCityButton: Button? = null
    private var selectedCity: String? = null

    private val citiesToClocks = mutableMapOf<ImageView, String>()
    private val cityButtons = mutableMapOf<Button, String>()
    private val usedCityButtons = mutableSetOf<Button>()
    private val usedClocks = mutableSetOf<ImageView>()

    private val correctMatches = mapOf(
        "London" to R.mipmap.london,
        "Moscow" to R.mipmap.moskow,
        "LosAngeles" to R.mipmap.losangeles,
        "Gonkong" to R.mipmap.gonkong,
        "Paris" to R.mipmap.paris,
        "Cidney" to R.mipmap.cidney,
        "Tokio" to R.mipmap.tokio,
        "Newyourk" to R.mipmap.newyourk,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game)

        initGameElements()

        // Добавлен обработчик событий для кнопки "Проверить"
        findViewById<Button>(R.id.HeyButton).setOnClickListener {
            val intent = Intent(this, ScoreActivity::class.java).apply {
                putExtra("SCORE", score)
            }
            startActivity(intent)
        }
    }

    private fun initGameElements() {
        // Инициализация часов
        val cityClockIds = listOf(
            Pair(R.id.LosAngelesClock, "LosAngeles"),
            Pair(R.id.londonClock, "London"),
            Pair(R.id.moscowClock, "Moscow"),
            Pair(R.id.TokioClock, "Tokio"),
            Pair(R.id.NewYourkClock, "Newyourk"),
            Pair(R.id.ParisClock, "Paris"),
            Pair(R.id.CidneyClock, "Cidney"),
            Pair(R.id.GonKongClock, "Gonkong")
        )

        for ((clockId, city) in cityClockIds) {
            val clock: ImageView = findViewById(clockId)
            citiesToClocks[clock] = city
            clock.setOnClickListener {
                if (!usedClocks.contains(clock)) {
                    selectClock(clock)
                    selectedCity?.let { city ->
                        checkMatch(city, clock)
                    }
                }
            }
        }

        // Инициализация кнопок для городов
        val cityButtonIds = listOf(
            Pair(R.id.LosAngelesButton, "LosAngeles"),
            Pair(R.id.londonButton, "London"),
            Pair(R.id.moscowButton, "Moscow"),
            Pair(R.id.TokioButton, "Tokio"),
            Pair(R.id.NewYourkButton, "Newyourk"),
            Pair(R.id.ParisButton, "Paris"),
            Pair(R.id.CidneyButton, "Cidney"),
            Pair(R.id.GonKongButton, "Gonkong")
        )

        for ((buttonId, city) in cityButtonIds) {
            val button: Button = findViewById(buttonId)
            cityButtons[button] = city
            button.setOnClickListener {
                if (!usedCityButtons.contains(button)) {
                    selectedCity = city
                    selectedCityButton = button
                    selectedClock?.let { clock ->
                        checkMatch(city, clock)
                    }
                }
            }
        }
    }

    private fun selectClock(clock: ImageView) {
        // Отмена выделения предыдущих часов, если таковые имеются
        selectedClock?.setBackgroundColor(
            ContextCompat.getColor(
                this,
                R.color.colorSelected
            )
        ) // серый

        selectedClock = clock

        // Установка фона для выбранных часов
        clock.setBackgroundColor(ContextCompat.getColor(this, R.color.colorSelected))
    }

    private fun checkMatch(city: String, clock: ImageView) {
        val correctClockResId = correctMatches[city]
        val isMatch = clock.drawable.constantState == ContextCompat.getDrawable(
            this,
            correctClockResId!!
        )?.constantState

        if (isMatch) {
            // Если соответствие правильное, меняем цвет на зеленый.
            clock.setBackgroundColor(ContextCompat.getColor(this, R.color.colorCorrect))
            selectedCityButton?.setBackgroundColor(ContextCompat.getColor(this, R.color.colorCorrect))
            score++
        } else {
            // Если соответствие неправильное, меняем цвет на красный.
            clock.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWrong))
            selectedCityButton?.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWrong))
        }

        // Сброс выбора для следующего раунда и блокировка элементов
        resetSelection()
        clock.isClickable = false
        selectedCityButton?.isClickable = false
    }

    private fun resetSelection() {
        selectedClock = null
        selectedCity = null
        selectedCityButton = null
    }
}
