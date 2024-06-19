package com.example.booksapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.booksapp.model.Book
import com.example.booksapp.viewmodels.BookViewModel

@Composable
fun BookListScreen(books: List<Book>, onBookClick: (Book) -> Unit) {
    LazyColumn {
        items(books) { book ->
            BookListItem(book = book, onClick = { onBookClick(book) })
        }
    }
}
