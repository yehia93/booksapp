package com.example.booksapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.booksapp.model.Book

@Dao
interface Dao {
    @Query("SELECT * FROM books ORDER BY releaseDate DESC")
    fun getAllBooks(): LiveData<List<Book>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(books: List<Book>)

    @Query("DELETE FROM books")
    suspend fun clearAll()
}