package com.example.applaudo.presentation.screens.lists

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.example.applaudo.common.Constants
import com.example.applaudo.data.local.db.AiringTodayTvShowCache
import com.example.applaudo.data.local.db.PopularTvShowCache
import com.example.applaudo.presentation.Screen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AiringTodayShowListContent(
    allMovies: LazyPagingItems<AiringTodayTvShowCache>,
    navController: NavController
) {

    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        content = {
            items(allMovies.itemSnapshotList){ tvShows ->
                tvShows?.let { AiringTodayShowListItem(tvShow = it, navController = navController) }
            }
        }
    )
}
@Composable
fun AiringTodayShowListItem(tvShow: AiringTodayTvShowCache, navController: NavController) {
    Card(
        modifier = Modifier
            .padding(bottom = 8.dp, start = 8.dp, end = 8.dp),
        elevation = 4.dp,
        backgroundColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .clickable {
                    navController.navigate(route = Screen.AiringTodayTvShowDetails.passTvShowId(tvShow.id.toString()))
                },
        ) {
            Image(
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth(),
                painter = rememberImagePainter(
                    data = Constants.POSTER_URL + tvShow.poster_path, builder = {
                        crossfade(true)
                        scale(Scale.FILL)
                    }),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
            Column(
                Modifier.padding(end = 2.dp, start = 8.dp, bottom = 8.dp, top = 8.dp),
                horizontalAlignment = Alignment.Start) {
                Text(text = tvShow.name, style = MaterialTheme.typography.body1, fontSize = 13.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Spacer(modifier = Modifier.height(4.dp))
                RatingComponent(rating = tvShow.vote_average.toString())
            }
        }
    }
}
