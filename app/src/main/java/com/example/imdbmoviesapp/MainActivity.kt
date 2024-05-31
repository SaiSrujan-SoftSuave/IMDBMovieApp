package com.example.imdbmoviesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.imdbmoviesapp.presentation.movies.nowplaying.MovieViewModel
import com.example.imdbmoviesapp.presentation.movies.nowplaying.NowPlayingScreen
import com.example.imdbmoviesapp.ui.theme.IMDBMoviesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IMDBMoviesAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NowPlayingScreen(Modifier.padding(innerPadding).fillMaxSize())
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IMDBMoviesAppTheme {
        Greeting("Android")
    }
}