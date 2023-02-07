package com.example.saharamusic.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saharamusic.model.SongResponse
import com.example.saharamusic.rest.SaharaRepository
import com.example.saharamusic.utils.UIState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "SaharaViewModel"

class SaharaViewModel @Inject constructor(
    private val saharaRepository: SaharaRepository,
    private val ioDispatcher: CoroutineDispatcher
): ViewModel() {

    private val genres by lazy {
        arrayListOf("house")
    }

    private val _houseSongs : MutableLiveData<UIState<SongResponse>> = MutableLiveData(UIState.LOADING)
    val houseSongs : MutableLiveData<UIState<SongResponse>> get() = _houseSongs

    init {
        getSongs()
    }

    private fun getSongs() {
        val options: HashMap<String, String> = hashMapOf()
        options.put("term", "")
        options.put("amp;media", "music")
        options.put("amp;entity", "song")
        options.put("amp;limit", "50")

        Log.d(TAG, "getSongs: $options")

        genres.forEach{genre ->
            options["term"] = genre
            run {
                viewModelScope.launch(ioDispatcher) {
                    saharaRepository.getListByGenre(options).collect() {
                        when (genre) { // update this with a HashMap<String, MutableLiveData<UIState<SongResponse>>>
                            "house" -> _houseSongs.postValue(it)
                        }
                    }
                }
            }
        }
    }
}