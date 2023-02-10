package com.example.saharamusic.di

import android.content.Context
import androidx.room.Room
import com.example.saharamusic.database.SaharaDAO
import com.example.saharamusic.database.SaharaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
    @Provides
    fun providesSaharaDatabase(@ApplicationContext context: Context): SaharaDatabase =
        Room.databaseBuilder(
            context,
            SaharaDatabase::class.java,
            "starwars-db"
        ).build()

    @Provides
    fun providesSaharaDAO(
        saharaDatabase: SaharaDatabase
    ): SaharaDAO =
        saharaDatabase.getSaharaDAO()
}