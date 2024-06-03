package com.example.imdbmoviesapp.presentation.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdbmoviesapp.core.NetworkResult
import com.example.imdbmoviesapp.domain.usecases.getnowpalyingmovies.GetNowPlayingMovies
import com.example.imdbmoviesapp.domain.usecases.getpopularmovies.GetPopularMovies
import com.example.imdbmoviesapp.domain.usecases.gettoprated.GetTopRatedMovies
import com.example.imdbmoviesapp.domain.usecases.getupcomingmovies.GetUpcomingMovies
import com.example.imdbmoviesapp.presentation.home_screen.state.HomeState
import com.example.imdbmoviesapp.presentation.home_screen.state.NowPlayingMoviesState
import com.example.imdbmoviesapp.presentation.home_screen.state.PopularMoviesState
import com.example.imdbmoviesapp.presentation.home_screen.state.TopRatedMoviesState
import com.example.imdbmoviesapp.presentation.home_screen.state.UpcomingMoviesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUpcomingMovies: GetUpcomingMovies,
    private val getTopRatedMovies: GetTopRatedMovies,
    private val getPopularMovies: GetPopularMovies,
    private val getNowPlayingMovies: GetNowPlayingMovies
) : ViewModel() {
    private val _homeState = MutableStateFlow<HomeState>(HomeState())
    val homeState = _homeState.asStateFlow()

    private val _upcomingMovies = MutableStateFlow<UpcomingMoviesState>(UpcomingMoviesState())
    val upcomingMovies = _upcomingMovies.asStateFlow()

    private val _topRatedMovies = MutableStateFlow<TopRatedMoviesState>(TopRatedMoviesState())
    val topRatedMovies = _topRatedMovies.asStateFlow()

    private val _popularMovies = MutableStateFlow<PopularMoviesState>(PopularMoviesState())
    val popularMovies = _popularMovies.asStateFlow()

    private val _nowPlayingMovies = MutableStateFlow<NowPlayingMoviesState>(NowPlayingMoviesState())
    val nowPlayingMovies = _nowPlayingMovies.asStateFlow()

    init {
        getUpcomingMovies()
        getTopRatedMovies()
        getPopularMovies()
        getNowPlayingMovies()
    }

    private fun getUpcomingMovies() {

        getUpcomingMovies.invoke().onEach { result ->
            when (result) {
                is NetworkResult.Error -> {
                    _upcomingMovies.value =
                        UpcomingMoviesState(error = result.message ?: "Network Error")
                }

                is NetworkResult.Loading -> {
                    _upcomingMovies.value = UpcomingMoviesState(isLoading = true)
                }

                is NetworkResult.Success -> {
                    _upcomingMovies.value = UpcomingMoviesState(data = result.data, isLoading = false)
                }
            }

        }.launchIn(viewModelScope)
    }

    private fun getTopRatedMovies() {

        getTopRatedMovies.invoke() .onEach { result ->
            when (result) {
                is NetworkResult.Error -> {
                    _topRatedMovies.value =
                        TopRatedMoviesState(error = result.message ?: "Network Error")
                }

                is NetworkResult.Loading -> {
                    _topRatedMovies.value = TopRatedMoviesState(isLoading = true)
                }

                is NetworkResult.Success -> {
                    _topRatedMovies.value = TopRatedMoviesState(data = result.data, isLoading = false)
                }
            }
        }.launchIn(viewModelScope)

    }

    private fun getPopularMovies() {

        getPopularMovies.invoke() .onEach { result ->
            when (result) {
                is NetworkResult.Error -> {
                    _popularMovies.value =
                        PopularMoviesState(error = result.message ?: "Network Error")
                }

                is NetworkResult.Loading -> {
                    _popularMovies.value = PopularMoviesState(isLoading = true)
                }

                is NetworkResult.Success -> {
                    _popularMovies.value = PopularMoviesState(data = result.data, isLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getNowPlayingMovies() {
        getNowPlayingMovies.invoke().onEach { result ->
            when (result) {
                is NetworkResult.Error -> {
                    _nowPlayingMovies.value =
                        NowPlayingMoviesState(error = result.message ?: "Network Error")
                }

                is NetworkResult.Loading -> {
                    _nowPlayingMovies.value = NowPlayingMoviesState(isLoading = true)
                }

                is NetworkResult.Success -> {
                    _nowPlayingMovies.value = NowPlayingMoviesState(data = result.data, isLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }

}