package com.example.bookexplorer.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bookexplorer.ui.components.BookBox
import com.example.bookexplorer.ui.components.CustomDialog
import com.example.bookexplorer.ui.components.DetailsBox
import com.example.bookexplorer.util.ServiceHelper


@Composable
fun BookScreen(viewModel: BookScreenViewModel = hiltViewModel()) {

    val bookState = viewModel.bookState.value

    Surface(modifier = Modifier.background(Color.Gray)) {
        Scaffold(topBar = {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(text = "Search") },
                value = bookState.searchText,
                onValueChange = { viewModel.onBookEvent(BookEvent.SearchTextChanged(it)) })

        })
        { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxSize(),
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(3.dp)
                ) {
                    itemsIndexed(bookState.bookListFiltered) { index, book ->
                        BookBox(book = book) { viewModel.onBookEvent(BookEvent.BookClicked(book.detailsId)) }
                    }

                }
            }
        }

    }
    AnimatedVisibility(
        visible = bookState.showDetailsBox,
        enter = slideInVertically(initialOffsetY = { 600 }) + fadeIn(
            initialAlpha = 0.2f
        ),
        exit = slideOutVertically(targetOffsetY = { 600 }) + fadeOut(targetAlpha = 0.2f)
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            DetailsBox(
                isLoading = bookState.isLoading,
                details = bookState.selectedBookDetails,
                onCloseClick = { viewModel.onBookEvent(BookEvent.ToggleDetailsBox(false)) },
                onFavoriteClick = {
                    viewModel.onBookEvent(
                        BookEvent.OnFavoriteClicked(bookState.selectedBookDetails?.bookId, it)
                    )
                })
        }
    }
    if(bookState.showErrorDialog) {
        CustomDialog(
            confirmButtonText = "Ok",
            messageText = "No connection to Inventory Manager",
            titleText = "Inventory Manager",
            onConfirm = { viewModel.onBookEvent(BookEvent.ToggleErrorDialog(false)) }) {

        }
    }
}