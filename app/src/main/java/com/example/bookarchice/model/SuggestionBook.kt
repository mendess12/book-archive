package com.example.bookarchice.model

// TODO var yapmaya gerek yok, nullable olma ihtiyaci olan datalar nullable birakilmali ve defaultlari null ise null verilmeli
data class SuggestionBook(
    val name: String?,
    val author: String?,
    val subject: String?,
    val type: String?
)