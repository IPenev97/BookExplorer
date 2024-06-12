package com.example.bookexplorer.ui

sealed class BookEvent {
    data class SearchTextChanged(val query: String) : BookEvent()
    data class SearchBarClicked(val isSearching: Boolean) : BookEvent()
    data class BookClicked(val id: Int) : BookEvent()
    data class ToggleDetailsBox(val show: Boolean) : BookEvent()
    data class OnFavoriteClicked(val bookIndex: Int?, val favorite: Boolean) : BookEvent()
    data class ToggleErrorDialog(val show: Boolean) : BookEvent()
}