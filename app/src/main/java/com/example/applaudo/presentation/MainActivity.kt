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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.applaudo.common.ConnectivityObserver
import com.example.applaudo.common.NetworkConnectivityObserver
import com.example.applaudo.presentation.login.LoginScreen
import com.example.applaudo.presentation.splash_screen.SplashScreen
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
                val status by connectivity.observe().collectAsState(
                    initial = ConnectivityObserver.Status.Unavailable
                )
                when (status) {
                    ConnectivityObserver.Status.Available -> Navigation()
                    ConnectivityObserver.Status.Lost -> {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Text(
                                text = "No Internet Connection",
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
    NavHost(navController = navController, startDestination = "splash_screen") {
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.MainScreen.route) {
            LoginScreen(navController = navController)
        }
    }
}