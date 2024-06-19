package com.example.booksapp.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CoroutineScope(Dispatchers.Main).launch {
            delay(SPLASH_SCREEN_DELAY_MS) // Delay in milliseconds

            // After delay, navigate to MainActivity
            startActivity(Intent(this@SplashScreen, MainActivity::class.java))
            finish() // Finish splash screen activity
        }
    }

    companion object {
        private const val SPLASH_SCREEN_DELAY_MS = 2000L // 2 seconds delay
    }
}
