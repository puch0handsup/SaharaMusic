package com.example.saharamusic.utils


sealed class UIState<out T> {
    object LOADING: UIState<Nothing>()
    data class SUCCESS<T>(val results: T): UIState<T>()
    data class ERROR(val error: Exception): UIState<Nothing>()
}
