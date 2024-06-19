package com.example.booksapp.viewmodels

import BookRepository
import android.util.Log
import androidx.lifecycle.*
import com.example.booksapp.model.Book
import kotlinx.coroutines.launch

class BookViewModel(private val repository: BookRepository) : ViewModel() {

    val books: LiveData<List<Book>> = repository.books

    init {
        fetchBooks()
    }

    fun fetchBooks() {
        viewModelScope.launch {
            try {
                repository.fetchBooks()
                Log.d("BookViewModel", "Books fetched successfully")
            } catch (e: Exception) {
                Log.e("BookViewModel", "Error fetching books: ${e.message}", e)
            }
        }
    }
}
