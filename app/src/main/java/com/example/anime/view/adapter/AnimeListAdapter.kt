package com.example.anime.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView
import com.example.anime.databinding.ItemAnimeBinding
import com.example.anime.model.Data
import com.squareup.picasso.Picasso

class AnimeListAdapter( private val onClick : OnItemClickListener) : RecyclerView.Adapter<AnimeListAdapter.ViewHolder>() {

    private var animeList = ArrayList<Data>()

    inner class ViewHolder(public val binding: ItemAnimeBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeListAdapter.ViewHolder {
        val binding = ItemAnimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnimeListAdapter.ViewHolder, position: Int) {
        val anime = animeList[position]
        holder.binding.animeTitle.text = anime.title
        holder.binding.animeRating.text = anime.rating
        holder.binding.animeEpisodes.text = "Episode : ${anime.episodes.toString()}"
        Picasso.get().load(anime.images?.jpg?.imageUrl).into(holder.binding.animeImage)

        holder.itemView.setOnClickListener {
            onClick.onItemClick(anime , anime.malId!!)
        }
    }

    override fun getItemCount(): Int {
        return animeList.size
    }

    fun setList(newData: ArrayList<Data>) {
        animeList.clear()
        animeList.addAll(newData)
        notifyDataSetChanged()

    }

    interface OnItemClickListener {
        fun onItemClick(anime: Data , animeId: Int)
    }

}