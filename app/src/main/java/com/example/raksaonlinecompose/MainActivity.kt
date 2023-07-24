package com.example.raksaonlinecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.raksaonlinecompose.route.ScreenRoute
import com.example.raksaonlinecompose.screen.HomeScreen
import com.example.raksaonlinecompose.screen.LoginScreen
import com.example.raksaonlinecompose.screen.SplashScreen
import com.example.raksaonlinecompose.ui.theme.RaksaOnlineComposeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RaksaOnlineComposeTheme {
                val context = LocalContext.current
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = ScreenRoute.SplashScreen.route
                ) {
                    composable(ScreenRoute.SplashScreen.route) {
                        SplashScreen(context){
//                            navController.navigate(ScreenRoute.LoginScreen.route)
                            navController.navigate(ScreenRoute.HomeScreen.route)
                        }
                    }

                    composable(ScreenRoute.LoginScreen.route) {
                        LoginScreen(context,navController){
                            if (it){
                                finish()
                            }
                        }
                    }

                    composable(ScreenRoute.HomeScreen.route){
                        HomeScreen()
                    }


                }
            }
        }


    }
}


