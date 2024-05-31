package com.example.imdbmoviesapp.core.di

import com.example.imdbmoviesapp.data.remote.api.MoviesApi
import com.example.imdbmoviesapp.data.remote.api.PersonsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideProductsService(retrofit: Retrofit): MoviesApi =
        retrofit.create(MoviesApi::class.java)

    @Provides
    @Singleton
    fun providePersonService(retrofit: Retrofit):PersonsApi =
        retrofit.create(PersonsApi::class.java)
}