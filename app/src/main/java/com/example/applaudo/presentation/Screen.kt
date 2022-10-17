package com.example.applaudo.presentation

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen")
    object MainScreen : Screen("main_screen")
    object HomeScreen: Screen("home_screen")

    object TvShowDetails : Screen("show_details_screen/{tvShowId}") {
        fun passTvShowId(tvShowId: String) = "show_details_screen/$tvShowId"
    }
    object TopRatedTvShowDetails : Screen("top_rated_show_details_screen/{tvShowId}") {
        fun passTvShowId(tvShowId: String) = "top_rated_show_details_screen/$tvShowId"
    }
    object OnTvTvShowDetails : Screen("on_tv_movie_details_screen/{tvShowId}") {
        fun passTvShowId(tvShowId: String) = "on_tv_movie_details_screen/$tvShowId"
    }
    object AiringTodayTvShowDetails : Screen("airing_today_show_details_screen/{tvShowId}") {
        fun passTvShowId(tvShowId: String) = "airing_today_show_details_screen/$tvShowId"
    }
}
