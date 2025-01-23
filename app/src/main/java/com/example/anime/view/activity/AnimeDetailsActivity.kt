package com.example.anime.view.activity

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.anime.R
import com.example.anime.databinding.ActivityAnimeDetailsBinding
import com.example.anime.model.AnimeDetails
import com.example.anime.util.CustomProgressDialog
import com.example.anime.util.Utilities
import com.example.anime.viewmodel.AnimeViewModel
import com.squareup.picasso.Picasso

class AnimeDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnimeDetailsBinding
    private lateinit var viewModel: AnimeViewModel
    private lateinit var loader: CustomProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAnimeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(AnimeViewModel::class.java)
        loader = CustomProgressDialog(this)

        binding.back.setOnClickListener { finish() }

        val animeId = intent.getIntExtra("animeId", 0)
        val animeTitle = intent.getStringExtra("animeTitle")

        if (animeTitle != null && animeId != 0) {
            if (Utilities.isInternetAvailable(this)) {
                viewModel.getAnimeDetails(animeId)
            }
            binding.title.text = animeTitle
        } else {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
        }

        viewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                loader.show()
            } else {
                loader.dismiss()
            }
        }

        viewModel.error.observe(this) { errorMessage ->
            if (errorMessage != null) {
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.animeDetails.observe(this) { animeDetails ->
            if (animeDetails != null) {
                setData(animeDetails)
            } else {
                Toast.makeText(this, "Details Not available", Toast.LENGTH_SHORT).show()
            }
        }

        setupWebView()
    }

    private fun setData(animeDetails: AnimeDetails) {
        binding.textTitle.text = animeDetails.data?.title ?: "N/A"
        binding.textEpisodes.text = "Episodes: ${animeDetails.data?.episodes ?: "N/A"}"
        binding.textRating.text = "Rating: ${animeDetails.data?.score ?: "N/A"}"
        binding.textSynopsis.text = animeDetails.data?.synopsis ?: "Synopsis not available"
        binding.textGenres.text =
            animeDetails.data?.genres?.joinToString(prefix = "Genres: ") { it.name!! }
                ?: "Genres: Not available"


        Picasso.get()
            .load(animeDetails.data?.images?.jpg?.imageUrl)
            .placeholder(R.drawable.placeholder_image)
            .error(R.drawable.image_error)
            .into(binding.imagePoster)

        val youtubeId = animeDetails.data?.trailer?.youtubeId
        if (youtubeId != null) {
            val trailerUrl = "https://www.youtube.com/embed/$youtubeId"
            PlayVideo(trailerUrl)
        } else {
            binding.webView.visibility = View.GONE

            binding.imagePoster.visibility = View.VISIBLE
        }
    }

    private fun PlayVideo(url: String) {

        binding.imagePoster.visibility = View.GONE
        binding.webView.visibility = View.VISIBLE
        val html = """
            <html>
            <body>
            <iframe width="100%" height="100%" src="$url" frameborder="0" allowfullscreen></iframe>
            </body>
            </html>
        """
        binding.webView.loadData(html, "text/html", "utf-8")
    }

    private fun setupWebView() {
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.mediaPlaybackRequiresUserGesture = false
        binding.webView.settings.allowContentAccess = true
        binding.webView.settings.domStorageEnabled = true
        binding.webView.settings.loadWithOverviewMode = true
        binding.webView.settings.useWideViewPort = true

        binding.webView.webViewClient = object : WebViewClient() {
            override fun onReceivedError(
                view: WebView?,
                errorCode: Int,
                description: String?,
                failingUrl: String?
            ) {
                super.onReceivedError(view, errorCode, description, failingUrl)
                Toast.makeText(
                    this@AnimeDetailsActivity,
                    "Error loading video: $description",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}

