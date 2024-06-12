package com.example.bookexplorer.ui

import com.example.bookexplorer.domain.Book
import com.example.bookexplorer.domain.BookDetails

data class BookState(
    val bookList: List<Book> = emptyList(),
    val bookListFiltered: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val showErrorDialog: Boolean = false,
    val searchText: String = "",
    val isSearching: Boolean = false,
    val showDetailsBox: Boolean = false,
    val selectedBookDetails: BookDetails? = null,

) {
}