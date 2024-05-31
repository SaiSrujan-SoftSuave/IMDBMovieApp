package com.example.imdbmoviesapp.presentation.movies.nowplaying

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdbmoviesapp.core.NetworkResult
import com.example.imdbmoviesapp.domain.repository.MovieRepository
import com.example.imdbmoviesapp.domain.usecases.getnowpalyingmovies.GetNowPlayingMovies
import com.example.imdbmoviesapp.presentation.movies.nowplaying.state.MovieState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieViewModel
@Inject constructor( val useCase: GetNowPlayingMovies) : ViewModel() {

    private val _movieData = MutableStateFlow<MovieState>(MovieState())
    val movieData: StateFlow<MovieState> = _movieData

    fun getNowPlayingMovies() {
        useCase().onEach { networkResult ->
            when (networkResult) {
                is NetworkResult.Error -> {
                    _movieData.value = MovieState(error = networkResult.message ?: "Network issue")
                }

                is NetworkResult.Loading -> {
                    _movieData.value = MovieState(isLoading = true)
                }

                is NetworkResult.Success -> {
                    _movieData.value = MovieState(data = networkResult.data, isLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }
}