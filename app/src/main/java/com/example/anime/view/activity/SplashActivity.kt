package com.example.anime.view.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.anime.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.time.delay
import java.util.logging.Handler
import kotlin.time.Duration.Companion.milliseconds

class SplashActivity : AppCompatActivity() {
    val context: Context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_splash_activiyt)

        val handler = android.os.Handler(Looper.getMainLooper())

        handler.postDelayed({
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)


    }
}