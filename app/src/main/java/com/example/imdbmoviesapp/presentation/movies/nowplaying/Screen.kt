package com.example.imdbmoviesapp.presentation.movies.nowplaying

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.olshevski.navigation.reimagined.hilt.hiltViewModel

@Composable
fun NowPlayingScreen(modifier: Modifier = Modifier,viewModel: MovieViewModel=hiltViewModel()) {
        val state by viewModel.movieData.collectAsState()
        Column(modifier = modifier, verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Button(onClick = { viewModel.getNowPlayingMovies() }) {
                        Text(text = "Now Playing")
                }
                if (state.isLoading){
                        Text(text = "Loading")
                }
                if(state.error.isNotBlank()){
                        Text(text = state.error)
                }
                 if (state.data != null){
                         Text(text = state.data.toString())
                 }
        }
}