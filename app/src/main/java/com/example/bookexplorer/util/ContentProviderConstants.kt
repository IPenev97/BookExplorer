package com.example.bookexplorer.util

import android.content.UriMatcher
import android.net.Uri


sealed class ContentProviderConstants {
    companion object{
        val URI_GET_BOOKS = Uri.parse( "content://com.example.inventorymanager.BooksContentProvider/books")
    }
}