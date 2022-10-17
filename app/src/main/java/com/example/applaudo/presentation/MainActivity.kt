package com.example.applaudo.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.applaudo.common.ConnectivityObserver
import com.example.applaudo.common.Constants
import com.example.applaudo.common.NetworkConnectivityObserver
import com.example.applaudo.presentation.screens.HomeScreen
import com.example.applaudo.presentation.screens.LoginScreen
import com.example.applaudo.presentation.screens.SplashScreen
import com.example.applaudo.presentation.screens.lists.AiringTodayShowListContent
import com.example.applaudo.presentation.screens.tv_show_details.airing_today.AiringTodayMovieDetailsScreen
import com.example.applaudo.presentation.screens.tv_show_details.on_tv.OnTvTvShowDetailsScreen
import com.example.applaudo.presentation.screens.tv_show_details.popular.MovieDetailsScreen
import com.example.applaudo.presentation.screens.tv_show_details.top_rated.TopRatedMovieDetailsScreen
import com.example.applaudo.presentation.ui.theme.ApplaudoTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var connectivity: NetworkConnectivityObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApplaudoTheme {
                val connectivityStatus by connectivity.observe().collectAsState(
                    initial = ConnectivityObserver.Status.Unavailable
                )
                when (connectivityStatus) {
                    ConnectivityObserver.Status.Available -> Navigation()
                    ConnectivityObserver.Status.Lost -> {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Text(
                                text = "Internet connection lost",
                                color = MaterialTheme.colors.error,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 20.dp)
                                    .align(Alignment.Center)
                            )
                        }
                    }
                    ConnectivityObserver.Status.Unavailable -> {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Text(
                                text = "Internet connection unavailable",
                                color = MaterialTheme.colors.error,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 20.dp)
                                    .align(Alignment.Center)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.MainScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.TvShowDetails.route,
            arguments = listOf(navArgument(Constants.TV_SHOW_DETAILS_ARGUMENT_KEY) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString(Constants.TV_SHOW_DETAILS_ARGUMENT_KEY)
                ?.let { MovieDetailsScreen(it,navController) }
        }
        composable(
            route = Screen.TopRatedTvShowDetails.route,
            arguments = listOf(navArgument(Constants.TV_SHOW_DETAILS_ARGUMENT_KEY) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString(Constants.TV_SHOW_DETAILS_ARGUMENT_KEY)
                ?.let { TopRatedMovieDetailsScreen(it,navController) }
        }
        composable(
            route = Screen.OnTvTvShowDetails.route,
            arguments = listOf(navArgument(Constants.TV_SHOW_DETAILS_ARGUMENT_KEY) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString(Constants.TV_SHOW_DETAILS_ARGUMENT_KEY)
                ?.let { OnTvTvShowDetailsScreen(it,navController) }
        }
        composable(
            route = Screen.AiringTodayTvShowDetails.route,
            arguments = listOf(navArgument(Constants.TV_SHOW_DETAILS_ARGUMENT_KEY) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString(Constants.TV_SHOW_DETAILS_ARGUMENT_KEY)
                ?.let { AiringTodayMovieDetailsScreen(it,navController) }
        }
    }
}