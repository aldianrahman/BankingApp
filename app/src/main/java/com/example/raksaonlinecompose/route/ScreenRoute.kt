package com.example.raksaonlinecompose.route

import com.example.raksaonlinecompose.entity.Card
import com.example.raksaonlinecompose.model.CardData

sealed class ScreenRoute(
    val route: String
){
    object SplashScreen: ScreenRoute("splashScreen")
    object HomeScreen: ScreenRoute("homeScreen")
    object LoginScreen: ScreenRoute("loginScreen")
    object SignupScreen: ScreenRoute("signupScreen")
    object ScanScreen: ScreenRoute("scanScreen")

    fun withArgs(args: List<Card>, index: Int): String {
        return buildString {
            if (index in args.indices) {
                append(route)
                val cardData = args[index]
                append("/${cardData.noCard}-${cardData.amount}")
            } else {
                append("Invalid index") // Handle invalid index case
            }
        }
    }

    fun withArgsString(vararg args: String):String{
        return buildString {
            append(route)
            args.forEach {args->
                append("/$args")
            }
        }
    }
}
