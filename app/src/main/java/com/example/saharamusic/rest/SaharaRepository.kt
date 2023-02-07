package com.example.saharamusic.rest

import com.example.saharamusic.model.SongResponse
import com.example.saharamusic.utils.FailureResponse
import com.example.saharamusic.utils.NullSongsResponse
import com.example.saharamusic.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface SaharaRepository {
    fun getListByGenre(options: Map<String, String>): Flow<UIState<SongResponse>>
}

class SaharaRepositoryImpl @Inject constructor(
    private val itunesAPI: ItunesAPI
) : SaharaRepository {

    override fun getListByGenre(options: Map<String, String>): Flow<UIState<SongResponse>> = flow {
        emit(UIState.LOADING)
        try {
            val response = itunesAPI.getListByGenre(options)
            if (response.isSuccessful){
                response.body()?.let {
                    emit(UIState.SUCCESS(it))
                }?: throw NullSongsResponse()
            } else
                throw FailureResponse(response.errorBody()?.string())
        }catch (e: Exception) {
            emit(UIState.ERROR(e))
        }
    }
}