package com.example.imdbmoviesapp.data.repository

import com.example.imdbmoviesapp.data.remote.api.MoviesApi
import com.example.imdbmoviesapp.data.remote.dto.MovieData
import com.example.imdbmoviesapp.data.remote.dto.MovieDetailDto
import com.example.imdbmoviesapp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val moviesApi: MoviesApi) : MovieRepository {
    override suspend fun getPopularMovies(): MovieData {
        return moviesApi.getPopularMovies()
    }

    override suspend fun getNowPlaying(): MovieData {
        return moviesApi.getNowPlaying()
    }

    override suspend fun getTopRated(): MovieData {
        return moviesApi.getTopRated()
    }

    override suspend fun getUpcoming(): MovieData {
        return moviesApi.getUpcoming()
    }

    override suspend fun getMovieDetails(movieId: Int): MovieDetailDto {
        return moviesApi.getDetailsById(movieId)
    }
}