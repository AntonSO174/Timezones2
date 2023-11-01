package com.example.myapplication

data class TimeZone(val city: String, val offset: Int)
val timeZones = listOf(
    TimeZone("Москва", 3),
    TimeZone("Лондон", 0),
    TimeZone("Нью-Йорк", -5),
    TimeZone("Лос-Анджелес", -8),
    TimeZone("Париж", 1),
    TimeZone("Сидней", 11),
    TimeZone("Токио", 9),
    TimeZone("Гонконг", 8)
)
