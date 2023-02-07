package com.example.saharamusic.utils

class NullSongsResponse(message: String = "Songs response is null"): Exception(message)
class FailureResponse(message: String?): Exception(message)