package com.example.applaudo.presentation

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen")
    object MainScreen : Screen("main_screen")
    object HomeScreen: Screen("home_screen")
}
