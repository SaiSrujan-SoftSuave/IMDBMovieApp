package com.example.imdbmoviesapp.presentation.home_screen.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.imdbmoviesapp.R
import com.example.imdbmoviesapp.core.constants.AppConstants
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)
@Composable
fun PosterViewPager(modifier: Modifier = Modifier, listOfPosterUrl: List<String> = listOf()) {
    val scope = rememberCoroutineScope()
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(2.dp))
            .fillMaxHeight(.6f)
            .fillMaxWidth()
    ) {
        val pagerState = rememberPagerState(pageCount = { listOfPosterUrl.size })
        HorizontalPager(state = pagerState) { iteration ->
            PagerItem(moviePosterUrl = listOfPosterUrl[iteration])
        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) {
                if (pagerState.currentPage == it) {
                    Text(text = "●")
                } else {
                    Text(text = "○")
                }
            }
        }
        LaunchedEffect(key1 = Unit) {
            scope.launch {
                pagerState.animateScrollToPage(pagerState.currentPage+1 % pagerState.pageCount)
                delay(2000)
            }
        }
    }
}

@Composable
fun PagerItem(
    modifier: Modifier = Modifier,
    moviePosterUrl: String = "${AppConstants.IMAGE_BASE_URL} /2ZkvqfOJUCINozB00wmYuGJQW81.jpg"
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(2.dp))
            .fillMaxHeight(.7f)
            .fillMaxWidth()
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(moviePosterUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.img_1),
            contentDescription = stringResource(R.string.app_name),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}


