package com.example.saharamusic.model


import com.google.gson.annotations.SerializedName
import retrofit2.http.Url

data class Song(
    @SerializedName("artistName")
    val artistName: String? = null,
    @SerializedName("artworkUrl60")
    val artworkUrl60: String? = null,
    @SerializedName("collectionArtistName")
    val collectionName: String? = null,
    @SerializedName("trackPrice")
    val trackPrice: Double? = null,
    @SerializedName("previewUrl")
    val previewUrl: Url? = null
)