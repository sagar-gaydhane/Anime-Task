package com.example.anime.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anime.databinding.ActivityMainBinding
import com.example.anime.model.Data
import com.example.anime.util.CustomProgressDialog
import com.example.anime.util.Utilities
import com.example.anime.view.adapter.AnimeListAdapter
import com.example.anime.viewmodel.AnimeViewModel

class MainActivity : AppCompatActivity(), AnimeListAdapter.OnItemClickListener {

    private lateinit var animeViewModel: AnimeViewModel
    private lateinit var loader: CustomProgressDialog
    private lateinit var adapter: AnimeListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        animeViewModel = ViewModelProvider(this).get(AnimeViewModel::class.java)
        loader = CustomProgressDialog(this)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.animeListRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = AnimeListAdapter(this)
        binding.animeListRecyclerView.adapter = adapter

        binding.swipeRefresh.setOnRefreshListener {
            fetchData()
            binding.swipeRefresh.isRefreshing = false
        }

        fetchData()
        errorResponse()
        handleLoadingTime()
        populateResponse()

    }

    private fun populateResponse() {
        animeViewModel.animeList.observe(this) { response ->
            if (response != null) {
                adapter.setList(response.data)
            }else{
                Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun handleLoadingTime() {
        animeViewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                loader.show()
            } else {
                loader.dismiss()
            }
        }
    }

    private fun errorResponse() {
        animeViewModel.error.observe(this) { error ->
            if (error != null) {
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun fetchData() {
        if (Utilities.isInternetAvailable(this)) {
            animeViewModel.getAnimeList()
        }
    }

    override fun onItemClick(anime: Data, animeId: Int) {
        if (Utilities.isInternetAvailable(this)) {
            startActivity(
                Intent(this, AnimeDetailsActivity::class.java).putExtra("animeId", animeId)
                    .putExtra("animeTitle", anime.title)
            )
        }
    }
}