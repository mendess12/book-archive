package com.example.bookarchice.model

import java.io.Serializable

data class Book(
    val bookName: String? = null,
    val bookAuthor: String? = null,
    val pageNumber: String? = null,
    val bookType: String? = null,
    val bookLanguage: String? = null,
    val bookPublisher: String? = null,
    val bookMessage: String? = null,
    val bookDate: String? = null,
    val userId: String? = null
) : Serializable