package com.example.anime.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast

object Utilities {

    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork = connectivityManager.activeNetwork ?: run {
            Toast.makeText(context, "No internet connection", Toast.LENGTH_SHORT).show()
            return false
        }

        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(activeNetwork) ?: run {
                Toast.makeText(context, "No internet connection", Toast.LENGTH_SHORT).show()
                return false
            }

        return when {
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
//                Toast.makeText(context, "Internet is available via WiFi", Toast.LENGTH_SHORT).show()
                true
            }
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
//                Toast.makeText(context, "Internet is available via Mobile Data", Toast.LENGTH_SHORT).show()
                true
            }
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
//                Toast.makeText(context, "Internet is available via Ethernet", Toast.LENGTH_SHORT).show()
                true
            }
            else -> {
                Toast.makeText(context, "No internet connection", Toast.LENGTH_SHORT).show()
                false
            }
        }
    }
}