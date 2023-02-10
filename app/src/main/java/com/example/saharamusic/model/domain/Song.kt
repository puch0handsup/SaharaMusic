package com.example.saharamusic.model.domain

import androidx.room.PrimaryKey
import com.example.saharamusic.model.SongItem
import com.google.gson.annotations.SerializedName

data class Song(
    @PrimaryKey val trackId: Long?,
    val artistName: String?,
    val artworkUrl60: String?,
    val collectionName: String?,
    val trackName: String?,
    val trackPrice: Double?,
    val collectionPrice: Double?,
    val trackTimeMillis: Long?,
    val previewUrl: String?,
    val genre: String?
)

fun List<SongItem>.mapToDomainSong(genre: String?): List<Song> =
    this.map {
        Song (
            trackId = it.trackId,
            artistName = it.artistName,
            artworkUrl60 = it.artworkUrl60,
            collectionName = it.collectionName,
            trackName = it.trackName,
            trackPrice = it.trackPrice,
            collectionPrice =  it.collectionPrice,
            trackTimeMillis = it.trackTimeMillis,
            previewUrl = it.previewUrl,
            genre = genre
        )
    }

