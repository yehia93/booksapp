package com.example.booksapp.model

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Book(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val title: String?,
    val description: String,
    val author: String,
    val releaseDate: String?,
    val imageUrl: String?
)
