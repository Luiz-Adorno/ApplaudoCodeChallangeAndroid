package com.example.applaudo.presentation

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen")
    object MainScreen : Screen("main_screen")
    object HomeScreen: Screen("home_screen")
    object MovieDetails : Screen("movie_details_screen/{movieId}") {
        fun passMovieId(movieId: String) = "movie_details_screen/$movieId"
    }
}
