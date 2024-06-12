package com.example.bookexplorer.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookexplorer.R
import com.example.bookexplorer.domain.BookDetails

@Composable
fun DetailsBox(
    details: BookDetails?,
    onFavoriteClick: (Boolean) -> Unit,
    onCloseClick: () -> Unit,
    isLoading: Boolean
) {
    val textHeaderStyle =
        TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.dark_orange)
        )
    val textStyle = TextStyle(fontSize = 16.sp, color = colorResource(id = R.color.dark_orange))
    Surface(
        shadowElevation = 10.dp,
        tonalElevation = 10.dp,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(0.7f)
            .fillMaxHeight(0.7f)
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
           , contentAlignment = Alignment.TopEnd) {
            Row() {
                if (details != null) {
                    if(!isLoading) {
                        IconButton(onClick = { onFavoriteClick(!details.isFavorite) }) {
                            if (details?.isFavorite == true)
                                Image(
                                    modifier = Modifier
                                        .size(60.dp)
                                        .padding(5.dp),
                                    painter = painterResource(id = R.drawable.favourite_star_filled),
                                    contentDescription = "Star Icon"
                                )
                            else
                                Image(
                                    modifier = Modifier
                                        .size(60.dp)
                                        .padding(5.dp),
                                    painter = painterResource(id = R.drawable.favourite_star_outlined),
                                    contentDescription = "Star Icon"
                                )
                        }
                    } else {
                        CircularProgressIndicator()
                    }
                }
                IconButton(onClick = { onCloseClick() }) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Close icon",
                        modifier = Modifier.background(Color.Red)
                    )
                }
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(10.dp)
        ) {
            if (details != null) {
                Text(
                    text = stringResource(id = R.string.genre) + details.genre,
                    style = textHeaderStyle
                )
                Text(
                    text = stringResource(id = R.string.published_on) + details.publishDate,
                    style = textStyle
                )
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp), color = colorResource(id = R.color.dark_orange)
                )
                Text(text = stringResource(id = R.string.summary), style = textHeaderStyle)
                Text(text = details.summery, style = textStyle)
            } else {
                Text(text = stringResource(id = R.string.no_details), style = textHeaderStyle)
            }
        }
    }
}