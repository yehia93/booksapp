package com.example.booksapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Card
import coil.compose.rememberImagePainter
import com.example.booksapp.model.Book


@Composable
fun BookListItem(book: Book, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Image(
            painter = rememberImagePainter(book.imageUrl),
            contentDescription = null,
            modifier = Modifier.size(64.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            book.title?.let { Text(it, style = MaterialTheme.typography.h6) }
            Text(book.description, maxLines = 2)
        }
    }
}