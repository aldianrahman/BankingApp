package com.example.raksaonlinecompose.route

sealed class ScreenRoute(
    val route: String
){
    object SplashScreen: ScreenRoute("splashScreen")
    object HomeScreen: ScreenRoute("homeScreen")
    object LoginScreen: ScreenRoute("loginScreen")
    object SignupScreen: ScreenRoute("signupScreen")
}
