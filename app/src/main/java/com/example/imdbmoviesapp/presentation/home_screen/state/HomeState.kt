package com.example.imdbmoviesapp.presentation.home_screen.state

import com.example.imdbmoviesapp.data.remote.dto.MovieData

data class HomeState(
     val isLoading: Boolean = false,
     val data: MovieData?=null,
     val error: String = ""
 )