package com.example.booksapp.network

import com.example.booksapp.model.Book
import retrofit2.http.GET

interface ApiService {
    @GET("/")
    suspend fun getBooks(): List<Book>
}