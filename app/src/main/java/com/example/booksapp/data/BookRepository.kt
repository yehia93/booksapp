import android.util.Log
import androidx.lifecycle.LiveData
import com.example.booksapp.R
import com.example.booksapp.data.BookDatabase // Ensure correct import
import com.example.booksapp.model.Book
import com.example.booksapp.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BookRepository(private val db: BookDatabase) {

    // LiveData representing all books from the database
    val books: LiveData<List<Book>> = db.bookDao().getAllBooks()

    suspend fun fetchBooks() {
        withContext(Dispatchers.IO) {
            var retryCount = 3
            val backoffDelay = 2000L // 2 seconds

            while (retryCount > 0) {
                try {
                    val bookList = RetrofitInstance.api.getBooks()
                    Log.d("BookRepository", "Fetched ${bookList.size} books from API")

                    // Adjust filtering logic to handle empty or null imageUrl
                    val validBooks = bookList.map { book ->
                        if (book.imageUrl.isNullOrEmpty()) {
                            // Provide a default image URL or handle as needed
                            book.copy(imageUrl = "https://dummyimage.com/100x100.png")
                        } else {
                            book // Keep the original book if imageUrl is valid
                        }
                    }
                    Log.d("BookRepository", "Filtered to ${validBooks.size} valid books with imageUrl")

                    // Insert only valid books into the database
                    if (validBooks.isNotEmpty()) {
                        db.bookDao().insertAll(validBooks)
                        Log.d("BookRepository", "Inserted ${validBooks.size} valid books into database")
                    } else {
                        Log.w("BookRepository", "No valid books to insert into database")
                    }

                    break // If successful, break out of the loop
                } catch (e: Exception) {
                    retryCount--
                    if (retryCount == 0) {
                        throw e
                    } else {
                        Log.e("BookRepository", "Error fetching books: ${e.message}", e)
                        Thread.sleep(backoffDelay)
                    }
                }
            }
        }
    }
}
