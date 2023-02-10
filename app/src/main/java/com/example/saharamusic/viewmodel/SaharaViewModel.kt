package com.example.saharamusic.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saharamusic.model.SongResponse
import com.example.saharamusic.rest.SaharaRepository
import com.example.saharamusic.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "SaharaViewModel"

@HiltViewModel
class SaharaViewModel @Inject constructor(
    private val saharaRepository: SaharaRepository
): ViewModel() {


    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    private val genres = arrayListOf("house", "rock", "pop", "classick", "reggaeton")
    private var isInitialized = false
    var songUri : String = ""


    private val _houseSongs : MutableLiveData<UIState<SongResponse>> = MutableLiveData(UIState.LOADING)
    val houseSongs : MutableLiveData<UIState<SongResponse>> get() = _houseSongs

    private val _rockSongs : MutableLiveData<UIState<SongResponse>> = MutableLiveData(UIState.LOADING)
    val rockSongs : MutableLiveData<UIState<SongResponse>> get() = _rockSongs

    private val _popSongs : MutableLiveData<UIState<SongResponse>> = MutableLiveData(UIState.LOADING)
    val popSongs : MutableLiveData<UIState<SongResponse>> get() = _popSongs

    private val _classicSongs : MutableLiveData<UIState<SongResponse>> = MutableLiveData(UIState.LOADING)
    val classicSongs : MutableLiveData<UIState<SongResponse>> get() = _classicSongs

    private val _reggaetonSongs : MutableLiveData<UIState<SongResponse>> = MutableLiveData(UIState.LOADING)
    val reggaetonSongs : MutableLiveData<UIState<SongResponse>> get() = _reggaetonSongs

    init {
        if (!isInitialized) {
            getSongs()
            isInitialized = true
        }
    }

    private fun getSongs() {
        genres.forEach{ genre ->
            run {
                viewModelScope.launch(ioDispatcher) {
                    saharaRepository.getListByGenre(genre).collect() {
                        when (genre) { // update this with a HashMap<String, MutableLiveData<UIState<SongResponse>>>
                            "house" -> _houseSongs.postValue(it)
                            "rock" -> _rockSongs.postValue(it)
                            "pop" -> _popSongs.postValue(it)
                            "classick" -> _classicSongs.postValue(it)
                            "reggaeton" -> _reggaetonSongs.postValue(it)
                        }
                    }
                }
            }
        }
    }

//    fun getSongs() {
//        val options: HashMap<String, String> = hashMapOf()
//        options.put("amp;media", "music")
//        options.put("term", "")
//        options.put("amp;entity", "song")
//        options.put("amp;limit", "50")
//        genres.forEach{genre ->
////            Log.d(TAG, "getSongs: genres.forEach/ item: $genre")
//            options.put("term", genre)
//            val sortedOptions = options.toSortedMap(compareBy<String> { it.length } .thenBy { it })
//            Log.d(TAG, "getSongs: $sortedOptions")
//
//            run {
//                viewModelScope.launch(ioDispatcher) {
//                    saharaRepository.getListByGenre(options).collect() {
//                        when (genre) { // update this with a HashMap<String, MutableLiveData<UIState<SongResponse>>>
//                            "house" -> _houseSongs.postValue(it)
//                        }
//                    }
//                }
//            }
//        }
//    }
}