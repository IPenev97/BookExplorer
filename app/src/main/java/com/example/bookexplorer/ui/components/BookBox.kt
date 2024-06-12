package com.example.bookexplorer.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.bookexplorer.R
import com.example.bookexplorer.domain.Book

@Composable
fun BookBox(book: Book, onClickBook: () -> Unit) {
    val titleTextStyle =
        TextStyle(fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.Bold)
    val authorTextStyle = TextStyle(fontSize = 15.sp, color = Color.White)

    Column(modifier = Modifier
        .border(
            width = 4.dp,
            color = colorResource(id = R.color.light_blue_button),
            shape = RoundedCornerShape(12.dp)
        )
        .clickable {onClickBook()}
        .background(
            colorResource(id = R.color.dark_blue_button), shape = RoundedCornerShape(12.dp)
        ), horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            modifier = Modifier
                .height(170.dp)
                .width(170.dp)
                .padding(5.dp),
            model = book.imageUrl,
            contentDescription = book.name
        )
        Text(text = book.name, style = titleTextStyle)
        Text(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 3.dp), text = book.author, style = authorTextStyle)
    }
}