package com.example.applaudo.presentation.splash_screen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.applaudo.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember {
        Animatable(0f)
    }
    //if pass true to key, this value will never change, running this coroutine only once
    LaunchedEffect(key1 = true) {
        //start the animation
        scale.animateTo(
            targetValue = 0.9f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(3000L)
        navController.navigate("main_screen")
    }

    // Creating a Vertical Gradient Color
    val gradientGrayWhite = Brush.verticalGradient(0f to Color(49,181,254), 1000f to Color(98,67,255))
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize().background(gradientGrayWhite)
    ) {
        Image(
            painter = painterResource(
                id = R.drawable.ic_app_logo
            ),
            contentDescription = "App logo",
            modifier = Modifier.scale(scale.value)
        )
    }
}