package com.example.bookexplorer.domain

data class Book(
    val id: Int,
    val name: String,
    val author: String,
    val imageUrl: String,
    val detailsId: Int



){
    fun matchSearchQuery(query: String) : Boolean{
        val matchingCombinations = listOf("$name$author", "$name $author", "$author $name")
        return matchingCombinations.any { it.contains(query, ignoreCase = true)}
    }
}
