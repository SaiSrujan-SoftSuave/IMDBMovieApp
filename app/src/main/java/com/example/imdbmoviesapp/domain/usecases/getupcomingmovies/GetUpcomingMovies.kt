package com.example.imdbmoviesapp.domain.usecases.getupcomingmovies

import android.os.Build
import android.os.ext.SdkExtensions
import com.example.imdbmoviesapp.core.NetworkResult
import com.example.imdbmoviesapp.data.remote.dto.MovieData
import com.example.imdbmoviesapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetUpcomingMovies @Inject constructor(private val moviesRepository: MovieRepository) {
    operator fun invoke(): Flow<NetworkResult<MovieData>> = flow {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && SdkExtensions.getExtensionVersion(
                Build.VERSION_CODES.S
            ) >= 7
        ) {
            try {
                emit(NetworkResult.Loading())

                val movies = moviesRepository.getUpcoming()
                emit(NetworkResult.Success(movies))

            } catch (e: IOException) {
                emit(NetworkResult.Error(e.localizedMessage))
            } catch (e: IOException) {
                emit(NetworkResult.Error(e.localizedMessage))
            }
        }
    }
}