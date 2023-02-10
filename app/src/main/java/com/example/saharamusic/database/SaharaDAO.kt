package com.example.saharamusic.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.saharamusic.model.domain.Song

@Dao
interface SaharaDAO {
    @Query("SELECT * FROM songs")
    suspend fun getSongs(): List<Song>

    @Insert(
        entity = Song::class,
        onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSongs(vararg song: Song)
}