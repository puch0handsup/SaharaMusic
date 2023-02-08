package com.example.saharamusic.di

import com.example.saharamusic.rest.SaharaRepository
import com.example.saharamusic.rest.SaharaRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn (ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun providesSaharaRepositoryImpl(saharaRepositoryImpl: SaharaRepositoryImpl) : SaharaRepository
}