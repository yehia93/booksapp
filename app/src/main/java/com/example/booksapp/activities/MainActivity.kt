package com.example.booksapp.activities

import BookRepository
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.booksapp.data.BookDatabase
import com.example.booksapp.model.Book
import com.example.booksapp.ui.BookDetailScreen
import com.example.booksapp.ui.BookListScreen
import com.example.booksapp.viewmodels.BookViewModel
import com.example.booksapp.viewmodels.BookViewModelFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize ViewModel, Repository, and Database
        val database = BookDatabase.getDatabase(this)
        val repository = BookRepository(database)
        val viewModelFactory = BookViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(BookViewModel::class.java)

        setContent {
            // Displaying the main content using Jetpack Compose
            MainActivityContent(viewModel = viewModel)
        }
    }
}

@Composable
fun MainActivityContent(viewModel: BookViewModel) {
    val navController = rememberNavController()

    // Collect the books LiveData into a Composable state
    val books by viewModel.books.observeAsState(emptyList())

    NavHost(navController = navController, startDestination = "bookList") {
        composable("bookList") {
            BookListScreen(books = books) { book ->
                navController.navigate("bookDetail/${book.id}")
            }
        }
        composable(
            route = "bookDetail/{bookId}",
            arguments = listOf(navArgument("bookId") { type = NavType.LongType })
        ) { backStackEntry ->
            val bookId = backStackEntry.arguments?.getLong("bookId")
            val selectedBook = books.find { it.id == bookId }
            selectedBook?.let {
                BookDetailScreen(book = it) {
                    navController.popBackStack()
                }
            }
        }
    }
}
