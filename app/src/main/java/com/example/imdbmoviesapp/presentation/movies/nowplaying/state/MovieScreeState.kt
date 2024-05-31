package com.example.imdbmoviesapp.presentation.movies.nowplaying.state

import com.example.imdbmoviesapp.data.remote.dto.MovieData

data class MovieState(
    val isLoading: Boolean = false,
    val data: MovieData?=null,
    val error: String = ""
)
