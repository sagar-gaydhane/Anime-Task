package com.example.anime.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anime.api.RetrofitInstance
import com.example.anime.model.AnimeDetails
import com.example.anime.model.TopAnimeResponse
import kotlinx.coroutines.launch

class AnimeViewModel() : ViewModel() {

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> get() = _error

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _animeList: MutableLiveData<TopAnimeResponse> = MutableLiveData()
    val animeList: LiveData<TopAnimeResponse> get() = _animeList

    fun getAnimeList() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                _isLoading.value = false
                val response = RetrofitInstance.apiService.getTopAnime()
                if (response.isSuccessful) {
                    _animeList.value = response.body()
                    Log.d("AnimeViewModel", "getAnimeList: $response")
                } else {
                    _error.value = response.message()
                    Log.d("AnimeViewModel", "getAnimeList: ${response.message()}")
                }
            } catch (e: Exception) {
                _isLoading.value = false
                _error.value = e.message
                Log.d("AnimeViewModel", "getAnimeList: ${e.message}")

            }

        }
    }


    private val _animeDetails: MutableLiveData<AnimeDetails> = MutableLiveData()
    val animeDetails: LiveData<AnimeDetails> get() = _animeDetails
    fun getAnimeDetails(animeId: Int) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                _isLoading.value = false
                val response = RetrofitInstance.apiService.getAnimeDetails(animeId)
                if (response.isSuccessful) {
                    _animeDetails.value = response.body()
                    Log.d("AnimeViewModel", "getAnimeDetails: $response")
                } else {
                    _error.value = response.message()
                    Log.d("AnimeViewModel", "getAnimeDetails: ${response.message()}")
                }
            } catch (e: Exception) {
                _isLoading.value = false
                _error.value = e.message
                Log.d("AnimeViewModel", "getAnimeDetails: ${e.message}")
            }
        }
    }


}