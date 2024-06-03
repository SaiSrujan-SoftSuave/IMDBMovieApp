package com.example.imdbmoviesapp.presentation.home_screen

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.imdbmoviesapp.core.constants.AppConstants.getImageUrl
import dev.olshevski.navigation.reimagined.hilt.hiltViewModel

@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: HomeViewModel = hiltViewModel()) {

    val popularMoviesState by viewModel.popularMovies.collectAsState()
    val topRatedMoviesState by viewModel.topRatedMovies.collectAsState()
    val upcomingMoviesState by viewModel.upcomingMovies.collectAsState()
    val nowPlayingMoviesState by viewModel.nowPlayingMovies.collectAsState()

    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyRowMovies(listOfMovieInfo = popularMoviesState.data?.results?.map {
            MovieCardInfo(
                posterUrl =  it.posterPath,
                title = it.title
            )
        } ?: emptyList(),
            isMovieLoading = remember {
                mutableStateOf(popularMoviesState.isLoading)
            },
            rowTitle = "Popular Movies")
        LazyRowMovies(listOfMovieInfo = topRatedMoviesState.data?.results?.map {
            MovieCardInfo(
                posterUrl =  it.posterPath,
                title = it.title
            )
        } ?: emptyList(),
            isMovieLoading =  remember {
                mutableStateOf(topRatedMoviesState.isLoading)
            },
            rowTitle = "Top Rated Movies")
        LazyRowMovies(listOfMovieInfo = upcomingMoviesState.data?.results?.map {
            MovieCardInfo(
                posterUrl =  it.posterPath,
                title = it.title
            )
        } ?: emptyList(),
            isMovieLoading = remember {
                                      mutableStateOf(upcomingMoviesState.isLoading)
            },
            rowTitle = "Upcoming Movies")
        LazyRowMovies(listOfMovieInfo = nowPlayingMoviesState.data?.results?.map {
            MovieCardInfo(
               posterUrl =  it.posterPath,
                title = it.title
            )
        } ?: emptyList(),
            isMovieLoading = remember {
                mutableStateOf(nowPlayingMoviesState.isLoading)
            } ,
            rowTitle = "Now Playing Movies")
    }

}


@Composable
fun LazyRowMovies(
    modifier: Modifier = Modifier,
    listOfMovieInfo: List<MovieCardInfo> = emptyList(),
    isMovieLoading: MutableState<Boolean> = mutableStateOf(false),
    rowTitle: String = "Popular Movies",
) {

    if (!isMovieLoading.value) {
        ListTitle(title = rowTitle)
        LazyRow(
            contentPadding = PaddingValues(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(4) {
                item {
                    MovieListCardLoading()
                }
            }
        }
    } else {
        ListTitle(title = rowTitle)
        LazyRow(
            contentPadding = PaddingValues(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(listOfMovieInfo.size) { index ->
                MovieListCard(
                    posterUrl = listOfMovieInfo[index].posterUrl,
                    title = listOfMovieInfo[index].title
                )
            }
        }

    }

}


@Composable
fun ListTitle(modifier: Modifier = Modifier, title: String = "Popular Movies") {
    Text(text = title, style = MaterialTheme.typography.titleMedium, modifier = modifier)
}

@Composable
fun MovieListCard(
    modifier: Modifier = Modifier,
    title: String = "The Shawshank Redemption ",
    posterUrl: String = "/gKkl37BQuKTanygYQG1pyYgLVgf.jpg"
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .width(150.dp)
                .height(230.dp)
                .clip(RoundedCornerShape(8.dp))
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(getImageUrl(posterUrl))
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = title,
            softWrap = true,
            maxLines = 2,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.width(150.dp),
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun MovieListCardLoading(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition(label = "alpha")
    val alpha by transition.animateFloat(
        initialValue = 0.4f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 1000
                0.7f at 500
            },
            repeatMode = RepeatMode.Reverse
        ), label = "alpha"
    )
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .width(150.dp)
                .height(230.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.LightGray.copy(alpha = alpha))
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(18.dp))
                .background(Color.LightGray.copy(alpha = alpha))
                .width(150.dp)
                .height(20.dp)
        )
    }

}

@Preview(showBackground = true)
@Composable
fun MovieListCardPreview(modifier: Modifier = Modifier) {

    HomeScreen()
}

data class MovieCardInfo(val title: String, val posterUrl: String)