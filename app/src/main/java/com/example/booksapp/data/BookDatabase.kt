package com.example.booksapp.data


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.booksapp.model.Book

@Database(entities = [Book::class], version = 3, exportSchema = false)
abstract class BookDatabase : RoomDatabase() {

    abstract fun bookDao(): Dao

    companion object {
        @Volatile private var instance: BookDatabase? = null

        fun getDatabase(context: Context): BookDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    BookDatabase::class.java, "books_database"
                )
                    .fallbackToDestructiveMigration()
                    .build().also { instance = it }
            }
        }
    }
}