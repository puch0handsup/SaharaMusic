package com.example.saharamusic.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.saharamusic.model.domain.Song


@Database(
    entities = [
        Song::class
    ],
    version = 1
)
abstract class SaharaDatabase : RoomDatabase() {
    abstract fun getSaharaDAO(): SaharaDAO
}