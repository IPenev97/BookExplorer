package com.example.bookexplorer.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookexplorer.data.BookRepository
import com.example.bookexplorer.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookScreenViewModel
@Inject constructor(private val bookRepository: BookRepository) : ViewModel() {


    private val _bookState = mutableStateOf(BookState())
    val bookState: State<BookState> = _bookState

    public fun onBookEvent(bookEvent: BookEvent) {
        when(bookEvent){
            is BookEvent.SearchTextChanged -> {
                val bookListFiltered = _bookState.value.bookList.filter{it.matchSearchQuery(bookEvent.query)}
                _bookState.value = bookState.value.copy(searchText = bookEvent.query, bookListFiltered = bookListFiltered)
            }
            is BookEvent.SearchBarClicked -> {
                _bookState.value = bookState.value.copy(isSearching = bookEvent.isSearching)
            }

            is BookEvent.BookClicked -> {
                viewModelScope.launch {
                    bookRepository.getBookDetails(bookEvent.id).collectLatest{ result ->
                        when(result){
                            is Resource.Error -> {
                                _bookState.value = bookState.value.copy(showErrorDialog = true)

                            }
                            is Resource.Loading -> {

                            }
                            is Resource.Success -> {
                                _bookState.value = bookState.value.copy(selectedBookDetails = result.data, showDetailsBox = true)
                            }
                        }
                    }
                }
            }

            is BookEvent.OnFavoriteClicked -> {
                viewModelScope.launch {

                    bookRepository.setBookFavorite(bookEvent.bookIndex, bookEvent.favorite).collectLatest { result ->
                        when(result) {
                            is Resource.Error -> {
                                _bookState.value = bookState.value.copy(isLoading = false, showErrorDialog = true)
                            }
                            is Resource.Loading -> {
                                _bookState.value = bookState.value.copy(isLoading = true)

                            }
                            is Resource.Success -> {
                                _bookState.value = bookState.value.copy(selectedBookDetails = result.data, isLoading = false)

                            }
                        }
                    }


                }
            }
            is BookEvent.ToggleDetailsBox -> {
                _bookState.value = bookState.value.copy(showDetailsBox = bookEvent.show)
            }

            is BookEvent.ToggleErrorDialog -> {
                _bookState.value = bookState.value.copy(showErrorDialog = bookEvent.show)
            }
        }
    }


    init {
        viewModelScope.launch {
            bookRepository.getAllBooks().collectLatest { result ->
                when (result) {
                    is Resource.Loading -> {

                    }

                    is Resource.Success -> {
                        if(result.data!=null)
                        _bookState.value = bookState.value.copy(bookList = result.data.toList(), bookListFiltered = result.data.toList())
                    }

                    is Resource.Error -> {
                        _bookState.value = bookState.value.copy(showErrorDialog = true)
                    }

                }

            }
        }
    }


}