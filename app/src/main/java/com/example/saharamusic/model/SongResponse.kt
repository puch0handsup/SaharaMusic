package com.example.saharamusic.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SongResponse(
    @Json(name = "resultCount")
    val resultCount: Int? = null,
    @Json(name = "results")
    val results: List<Song>? = null
)