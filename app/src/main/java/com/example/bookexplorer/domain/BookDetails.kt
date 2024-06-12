package com.example.bookexplorer.domain

data class BookDetails(
    val id: Int,
    val bookId: Int,
    val summery: String,
    val publishDate: String,
    val genre: String,
    val isFavorite: Boolean
) {

}