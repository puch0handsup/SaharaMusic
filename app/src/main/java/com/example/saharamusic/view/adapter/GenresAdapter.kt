package com.example.saharamusic.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.saharamusic.R
import com.example.saharamusic.databinding.RvSongItemBinding
import com.example.saharamusic.model.Song

class GenresAdapter (
    private val itemSet: MutableList<Song> = mutableListOf(),
    private val onItemClick: (previewUrl: String) -> Unit
        ) : RecyclerView.Adapter<GenresViewModel>(){

    fun updateItems(newItems: List<Song>) {
        if (itemSet != newItems) {
            itemSet.clear()
            itemSet.addAll(newItems)

            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresViewModel =
        GenresViewModel(
            RvSongItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: GenresViewModel, position: Int) =
        holder.bind(itemSet[position], onItemClick)


    override fun getItemCount(): Int {
        return itemSet.size
    }
}

class GenresViewModel(
    private val binding: RvSongItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun Double.format(digits: Int) = "%.${digits}f".format(this)

    fun bind(item: Song, onItemClick: (String) -> Unit) {
        binding.ivAlbumItem.setImageResource(R.drawable.baseline_headphones_24)
        binding.tvArtistNameItem.text = item.artistName
        binding.tvSongNameItem.text = item.trackName ?: item.collectionName
        val songPrice = if (item.trackName != null)
            "${item.trackPrice?.format(2).toString()} USD"
        else
            "${item.collectionPrice?.format(2).toString()} USD"

        binding.tvSongPriceItem.text = songPrice

        itemView.setOnClickListener {
            item.previewUrl?.let(onItemClick)
        }
        Glide
            .with(binding.root)
            .load(item.artworkUrl60)
            .centerCrop()
            .placeholder(R.drawable.baseline_image_search_24)
            .error(R.drawable.baseline_broken_image_24)
            .into(binding.ivAlbumItem)
    }
}