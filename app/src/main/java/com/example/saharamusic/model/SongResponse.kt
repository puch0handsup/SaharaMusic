package com.example.saharamusic.model


import com.google.gson.annotations.SerializedName
data class SongResponse(
    @SerializedName("resultCount")
    val resultCount: Int? = null,
    @SerializedName("results")
    val results: List<SongItem>? = null
)