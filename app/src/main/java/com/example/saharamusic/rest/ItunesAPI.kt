package com.example.saharamusic.rest

import com.example.saharamusic.model.SongResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ItunesAPI {

    @GET(SEARCH)
    suspend fun getListByGenre(
        @Query("term") term: String,
        @Query("amp;media", encoded = true) media: String = "music",
        @Query("amp;entity", encoded = true) entity: String = "song",
        @Query("amp;limit", encoded = true) limit: String = "50",
        ) : Response<SongResponse>

//    @GET(SEARCH)
//    suspend fun getListByGenre(
//        @QueryMap options: Map<String, String>
//    ) : Response<SongResponse>

    // https://itunes.apple.com/search?term=rock&amp;media=music&amp;entity=song&amp;limit=50
    // https://itunes.apple.com/search?term=house&amp;media=music&amp;entity=song&amp;limit=50
    companion object {
        const val BASE_URL = "https://itunes.apple.com/"
        private const val SEARCH = "search"
    }
}