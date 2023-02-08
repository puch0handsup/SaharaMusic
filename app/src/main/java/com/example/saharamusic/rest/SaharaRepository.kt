package com.example.saharamusic.rest

import android.util.Log
import com.example.saharamusic.model.SongResponse
import com.example.saharamusic.utils.FailureResponse
import com.example.saharamusic.utils.NullSongsResponse
import com.example.saharamusic.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface SaharaRepository {
    fun getListByGenre(term: String): Flow<UIState<SongResponse>>
//    fun getListByGenre(options: Map<String, String>): Flow<UIState<SongResponse>>
}
private const val TAG = "SaharaRepository"
class SaharaRepositoryImpl @Inject constructor(
    private val itunesAPI: ItunesAPI
) : SaharaRepository {

    override fun getListByGenre(term: String): Flow<UIState<SongResponse>> = flow {
        emit(UIState.LOADING)
        try {
            val response = itunesAPI.getListByGenre(term, "music", "song", "50")
            if (response.isSuccessful){
                response.body()?.let {
                    Log.d(TAG, "getListByGenre: $it")
                    emit(UIState.SUCCESS(it))
                }?: throw NullSongsResponse()
            } else
                throw FailureResponse(response.errorBody()?.string())
        }catch (e: Exception) {
            emit(UIState.ERROR(e))
        }
    }

//    override fun getListByGenre(options: Map<String, String>): Flow<UIState<SongResponse>> = flow {
//        emit(UIState.LOADING)
//        try {
//            val response = itunesAPI.getListByGenre(options)
//            if (response.isSuccessful){
//                response.body()?.let {
//                    Log.d(TAG, "getListByGenre: $it")
//                    emit(UIState.SUCCESS(it))
//                }?: throw NullSongsResponse()
//            } else
//                throw FailureResponse(response.errorBody()?.string())
//        }catch (e: Exception) {
//            emit(UIState.ERROR(e))
//        }
//    }
}