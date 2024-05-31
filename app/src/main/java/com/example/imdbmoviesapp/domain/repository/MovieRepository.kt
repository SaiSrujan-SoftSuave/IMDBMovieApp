package com.example.imdbmoviesapp.domain.repository

import com.example.imdbmoviesapp.data.remote.dto.MovieData
import retrofit2.http.GET

interface MovieRepository {


    suspend fun getPopularMovies(): MovieData


    suspend fun getNowPlaying(): MovieData


    suspend fun getTopRated(): MovieData


    suspend fun getUpcoming(): MovieData
}