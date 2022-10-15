package com.example.applaudo.presentation.screens.movie_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.example.applaudo.R
import com.example.applaudo.common.Constants
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState


@Composable
fun MovieDetailsScreen(
    movieId: String,
    navController: NavController,
    viewModel: MovieDetailsViewModel = hiltViewModel(),
) {
    viewModel.getMovieDetails(movieID = movieId.toInt())
    val movieDetails by viewModel.selectedMovie.collectAsState()

    val state = rememberCollapsingToolbarScaffoldState()

    CollapsingToolbarScaffold(
        modifier = Modifier,
        state = state,
        scrollStrategy = ScrollStrategy.EnterAlways,
        toolbar = {
            val textSize = (10 + (30 - 18) * state.toolbarState.progress).sp
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colors.primary)
                    .fillMaxWidth()
                    .height(350.dp)
                    .pin()
            )
            Image(
                painter = rememberImagePainter(
                    data = Constants.POSTER_URL + movieDetails?.posterPath, builder = {
                        crossfade(true)
                        scale(Scale.FIT)
                    }),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .drawWithCache {
                        onDrawWithContent {
                            drawContent()
                            drawRect(
                                Brush.verticalGradient(
                                    0.5f to Color.Black.copy(alpha = 0F),
                                    1F to Color.Black
                                )
                            )
                        }
                    },
                contentScale = ContentScale.FillWidth
            )

            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back Icon",
                    tint =  Color.White
                )
            }

            Column(modifier = Modifier
                .road(Alignment.BottomStart, Alignment.BottomStart)
                .padding(15.dp, 0.dp, 0.dp, 30.dp)) {
                movieDetails?.title?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.h5,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = textSize
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.rating_stars),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(
                            end = 2.dp,
                        ),
                    contentScale = ContentScale.FillWidth
                )
            }
        }){

        Column(modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
        ) {
            movieDetails?.overview?.let {
                Text(
                    text = stringResource(R.string.summary),
                    style = MaterialTheme.typography.body2,
                    color = Color(98, 67, 255),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 15.dp, end = 15.dp, top = 8.dp)
                )
                Text(
                    text = it,
                    style = MaterialTheme.typography.body2,
                    fontSize = 14.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 15.dp, end = 15.dp, top = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {

                items(20) {
                    Text(
                        text = "Item $it",
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}

