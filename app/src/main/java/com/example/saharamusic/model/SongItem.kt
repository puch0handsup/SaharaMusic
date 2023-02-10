package com.example.saharamusic.model


import com.google.gson.annotations.SerializedName

data class SongItem(
    @SerializedName("artistName")
    val artistName: String? = null,
    @SerializedName("artworkUrl60")
    val artworkUrl60: String? = null,
    @SerializedName("collectionName")
    val collectionName: String? = null,
    @SerializedName("trackName")
    val trackName: String? = null,
    @SerializedName("trackPrice")
    val trackPrice: Double? = null,
    @SerializedName("collectionPrice")
    val collectionPrice: Double? = null,
    @SerializedName("trackId")
    val trackId: Long? = null,
    @SerializedName("trackTimeMillis")
    val trackTimeMillis: Long? = null,
    @SerializedName("previewUrl")
    val previewUrl: String? = null
)