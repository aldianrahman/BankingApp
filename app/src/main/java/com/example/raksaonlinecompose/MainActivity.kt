package com.example.raksaonlinecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.raksaonlinecompose.dao.CardDao
import com.example.raksaonlinecompose.dao.HistoryDao
import com.example.raksaonlinecompose.db.AppDatabase
import com.example.raksaonlinecompose.route.ScreenRoute
import com.example.raksaonlinecompose.screen.HomeScreen
import com.example.raksaonlinecompose.screen.LoginScreen
import com.example.raksaonlinecompose.screen.SplashScreen
import com.example.raksaonlinecompose.ui.theme.RaksaOnlineComposeTheme
import com.example.raksaonlinecompose.screen.ScanQRScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RaksaOnlineComposeTheme {
                val context = LocalContext.current
                val db: CardDao = AppDatabase.getInstance(context)?.cardDao()!!
                val dbHistory: HistoryDao = AppDatabase.getInstance(context)?.historyDao()!!
                db.deleteAllCard()
                db.insertOrReplaceUser("5379 4130 2609 4968",5000000L)
                db.insertOrReplaceUser("5379 4130 2609 4969",600000L)
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
                        HomeScreen(navController,db)
                    }

                    composable(
                        ScreenRoute.ScanScreen.route+ "/{index}",
                        arguments = listOf(
                            navArgument("index"){
                                type = NavType.StringType
                                defaultValue = "Error Data"
                                nullable = true
                            }
                        )
                    ){entry->
                        ScanQRScreen(db,dbHistory,navController,entry.arguments?.getString("index"))
                    }


                }
            }
        }


    }
}


