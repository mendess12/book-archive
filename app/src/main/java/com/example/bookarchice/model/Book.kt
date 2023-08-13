package com.example.bookarchice.model

import java.io.Serializable

// TODO var yapmaya gerek yok, nullable olma ihtiyaci olan datalar nullable birakilmali ve defaultlari null ise null verilmeli
data class Book(
    var bookName: String?,
    var bookAuthor: String?,
    var pageNumber: String?,
    var bookType: String?,
    var bookLanguage: String?,
    var bookPublisher: String?,
    var bookMessage: String?,
    var bookDate: String?,
    var userId: String
) : Serializable
