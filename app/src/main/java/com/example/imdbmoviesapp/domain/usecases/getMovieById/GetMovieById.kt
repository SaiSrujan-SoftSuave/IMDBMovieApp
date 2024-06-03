package com.example.imdbmoviesapp.domain.usecases.getMovieById

import com.example.imdbmoviesapp.core.NetworkResult
import com.example.imdbmoviesapp.core.utils.runOnIOThread
import com.example.imdbmoviesapp.data.remote.dto.MovieDetailDto
import com.example.imdbmoviesapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetMovieById @Inject constructor(private val repository: MovieRepository) {
    operator fun invoke(movieId: Int): Flow<NetworkResult<MovieDetailDto>> = flow {
        try {
            emit(NetworkResult.Loading())
            runOnIOThread {
                val movies = repository.getMovieDetails(movieId)
                emit(NetworkResult.Success(movies))
            }
        } catch (e: IOException) {
            emit(NetworkResult.Error(e.localizedMessage))
        } catch (e: IOException) {
            emit(NetworkResult.Error(e.localizedMessage))
        }
    }
}