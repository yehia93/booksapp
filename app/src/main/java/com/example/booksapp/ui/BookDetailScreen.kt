package com.example.booksapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.booksapp.model.Book
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun BookDetailScreen(book: Book?, function: () -> Unit) {
    val dateFormat = SimpleDateFormat("EEE, MMM d, ''yy", Locale.getDefault())
    val inputDateFormat = SimpleDateFormat("d/MM/yyyy", Locale.getDefault())

    if (book == null) {
        return
    }

    val formattedDate = try {
        inputDateFormat.parse(book.releaseDate)?.let { dateFormat.format(it) }
    } catch (e: Exception) {
        null
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Image(
            painter = rememberImagePainter(book.imageUrl),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        book.title?.let { Text(it, style = MaterialTheme.typography.h4) }
        Text("by ${book.author}", style = MaterialTheme.typography.subtitle1)
        formattedDate?.let {
            Text("Released on: $it", style = MaterialTheme.typography.subtitle2)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(book.description ?: "")
    }
}
