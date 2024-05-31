package com.example.imdbmoviesapp.domain.usecases.getnowpalyingmovies

import com.example.imdbmoviesapp.core.NetworkResult
import com.example.imdbmoviesapp.data.remote.dto.MovieData
import com.example.imdbmoviesapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetNowPlayingMovies @Inject constructor(private val repository: MovieRepository) {

    operator fun invoke() :  Flow<NetworkResult<MovieData>> = flow{
        try {
            emit(NetworkResult.Loading())
            val movies = repository.getNowPlaying()
            emit(NetworkResult.Success(movies))
        } catch (e: IOException) {
            emit(NetworkResult.Error(e.localizedMessage))
        } catch (e: IOException) {
            emit(NetworkResult.Error(e.localizedMessage))
        }
    }

}