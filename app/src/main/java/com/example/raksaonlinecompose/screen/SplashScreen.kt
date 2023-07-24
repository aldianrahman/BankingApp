package com.example.raksaonlinecompose.screen

import android.content.Context
import android.widget.ImageView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.raksaonlinecompose.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    context : Context,
    navigateToNextScreen: () -> Unit
){
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.splash_screen_ver4),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        LaunchedEffect(key1 = true) {
            delay(3000) // Menunggu 3 detik sebelum melanjutkan ke layar berikutnya
            navigateToNextScreen()
        }
    }
}

