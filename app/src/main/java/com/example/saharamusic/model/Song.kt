package com.example.saharamusic.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import retrofit2.http.Url

@JsonClass(generateAdapter = true)
data class Song(
    @Json(name = "artistName")
    val artistName: String? = null,
    @Json(name = "artworkUrl60")
    val artworkUrl60: String? = null,
    @Json(name = "collectionArtistName")
    val collectionName: String? = null,
    @Json(name = "trackPrice")
    val trackPrice: Double? = null,
    @Json(name = "previewUrl")
    val previewUrl: Url? = null
)