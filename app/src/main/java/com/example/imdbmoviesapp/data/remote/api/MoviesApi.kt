package com.example.imdbmoviesapp.data.remote.api

import com.example.imdbmoviesapp.data.remote.dto.MovieData
import com.example.imdbmoviesapp.data.remote.dto.MovieDetailDto
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesApi {

    //https://api.themoviedb.org/3/movie/653346
    @GET("/3/movie/popular")
    suspend fun getPopularMovies(): MovieData

    @GET("/3/movie/now_playing")
    suspend fun getNowPlaying(): MovieData

    @GET("/3/movie/top_rated")
    suspend fun getTopRated(): MovieData

    @GET("/3/movie/upcoming")
    suspend fun getUpcoming(): MovieData

    @GET("/3/movie/{id}")
    suspend fun getDetailsById(@Path("id") id: Int): MovieDetailDto


}