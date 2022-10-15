package com.example.applaudo.presentation.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.applaudo.presentation.screens.lists.PopularMovieListContent
import com.example.applaudo.presentation.ui.theme.ButtonChipColor
import com.example.applaudo.presentation.viewmodels.HomeViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val popularMovies = viewModel.getAllPopularMovies.collectAsLazyPagingItems()
    val topRatedMovies = viewModel.getTopRatedMovies.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(240, 242, 245))
    ) {
        AppBar(navController)
        PageContent(navController, viewModel)
        PopularMovieListContent(popularMovies,navController)
       // TopRatedMovieListContent(topRatedMovies,navController)




    }
}
@Composable
fun AppBar(navController: NavController){
    TopAppBar(
        elevation = 4.dp,
        title = {
            Text("Tv Shows")
        },
        contentColor = Color.White,
        backgroundColor = Color(98,67,255),
        actions = {
            IconButton(onClick = {/*TODO*/ }) {
                Icon(Icons.Filled.Search, null)
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Person, null)
            }
        })
}

@Composable
fun PageContent(navController: NavController, viewModel: HomeViewModel){
    val listState: LazyListState = rememberLazyListState()
    val listOfContentType = listOf("Popular","Top Rated","On TV","Airing Today")
//     A LazyColumn is a vertically scrolling list that only composes and lays out the currently visible items.
//     It’s similar to a Recyclerview in the classic Android View system.
    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxWidth()
    ) {

        item {
            LazyRow(
                modifier = Modifier
                    .padding(horizontal = 2.dp)
                    .fillMaxSize()
            ) {
                items(count = listOfContentType.size) {
                    SelectableContentTypeChip(
                        contentTypeName = listOfContentType[it],
                        selected = listOfContentType[it] == viewModel.selectedType.value ,
                        onclick = {
                            if (viewModel.selectedType.value != listOfContentType[it]) {
                                viewModel.selectedType.value = listOfContentType[it]
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun SelectableContentTypeChip(
    contentTypeName: String,
    selected: Boolean,
    onclick: () -> Unit
) {

    val animateChipBackgroundColor by animateColorAsState(
        targetValue = if (selected) Color(98,67,255) else ButtonChipColor,
        animationSpec = tween(
            durationMillis = if (selected) 100 else 50,
            delayMillis = 0,
            easing = LinearOutSlowInEasing
        )
    )

    Box(
        modifier = Modifier
            .padding(end = 4.dp, top = 14.dp, bottom = 8.dp)
            .clip(CircleShape)
            .background(
                color = animateChipBackgroundColor
            )
            .height(32.dp)
            .widthIn(min = 80.dp)
            .padding(horizontal = 8.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                onclick()
            }
    ) {
        Text(
            text = contentTypeName,
            fontWeight = if (selected) FontWeight.Normal else FontWeight.Light,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.Center),
            color = if (selected) Color.White else Color(107,107,131)
        )
    }
}