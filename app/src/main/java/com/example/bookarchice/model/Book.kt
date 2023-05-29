package com.example.bookarchice.model

data class Book(
    var bookName: String?,
    var bookAuthor: String?,
    var pageNumber: String?,
    var bookType: String?,
    var bookLanguage: String?,
    var userId: String
){
    var expandable : Boolean = false
}
