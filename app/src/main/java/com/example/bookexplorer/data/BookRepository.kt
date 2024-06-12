package com.example.bookexplorer.data

import android.content.Context
import com.example.bookexplorer.domain.Book
import com.example.bookexplorer.domain.BookDetails
import com.example.bookexplorer.util.ContentProviderConstants
import com.example.bookexplorer.util.Error
import com.example.bookexplorer.util.Resource
import com.example.bookexplorer.util.ServiceHelper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BookRepository(
    context: Context,
    val serviceHelper: ServiceHelper
) {



    val contentResolver = context.contentResolver

    suspend fun getAllBooks(): Flow<Resource<MutableList<Book>>> {
        return flow {
            emit(Resource.Loading(true))
            val cursor = contentResolver.query(
                ContentProviderConstants.URI_GET_BOOKS,
                null,
                null,
                null,
                null
            );
            var bookList = mutableListOf<Book>()
            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val book = Book(id = cursor.getInt(0), name = cursor.getString(1), imageUrl = cursor.getString(2), detailsId = cursor.getInt(3), author = cursor.getString(4))
                    bookList.add(book)
                }
            }
            cursor?.close()
            if(bookList.isEmpty()) {
                emit(Resource.Error(message = Error.EMPTY_BOOK_LIST))
                return@flow
            }
            emit(Resource.Success(data = bookList))
        }
    }
    suspend fun getBookDetails(id: Int?) : Flow<Resource<BookDetails>>{
        return flow {
            if (id != null) {
                emit(Resource.Loading(true))
                val bookDetails: BookDetails? = serviceHelper.getBookDetails(id);
                if (bookDetails != null)
                    emit(Resource.Success(bookDetails))
                else
                    emit(Resource.Error(Error.CONNECTION_TO_SERVICE))

            } else {
                return@flow
            }
        }
    }
    suspend fun setBookFavorite(id: Int?, favorite: Boolean) : Flow<Resource<BookDetails>>{
        return flow {
            if (id != null) {
                emit(Resource.Loading(true))
                serviceHelper.setBookFavorite(id, favorite)
                delay(1000L)

                    val bookDetails: BookDetails? = serviceHelper.getBookDetails(id);
                    if (bookDetails != null)
                        emit(Resource.Success(bookDetails))
                    else
                        emit(Resource.Error(Error.CONNECTION_TO_SERVICE))


            } else
                return@flow
        }

    }


}