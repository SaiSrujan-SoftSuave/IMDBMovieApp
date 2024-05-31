package com.example.imdbmoviesapp.data.remote.api

import com.example.imdbmoviesapp.data.remote.dto.MovieData
import retrofit2.http.GET

interface MoviesApi {
    @GET("/3/movie/popular")
    suspend fun getPopularMovies(): MovieData

    @GET("/3/movie/now_playing")
    suspend fun getNowPlaying(): MovieData

    @GET("/3/movie/top_rated")
    suspend fun getTopRated(): MovieData

    @GET("/3/movie/upcoming")
    suspend fun getUpcoming(): MovieData


}