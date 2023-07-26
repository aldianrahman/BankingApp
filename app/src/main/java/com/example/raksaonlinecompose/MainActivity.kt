package com.example.raksaonlinecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.raksaonlinecompose.dao.CardDao
import com.example.raksaonlinecompose.dao.HistoryDao
import com.example.raksaonlinecompose.db.AppDatabase
import com.example.raksaonlinecompose.model.DetailTransaction
import com.example.raksaonlinecompose.model.PieChartInput
import com.example.raksaonlinecompose.route.ScreenRoute
import com.example.raksaonlinecompose.screen.HomeScreen
import com.example.raksaonlinecompose.screen.LoginScreen
import com.example.raksaonlinecompose.screen.SplashScreen
import com.example.raksaonlinecompose.ui.theme.RaksaOnlineComposeTheme
import com.example.raksaonlinecompose.screen.ScanQRScreen
import com.example.raksaonlinecompose.ui.theme.blue
import com.example.raksaonlinecompose.ui.theme.blueGray
import com.example.raksaonlinecompose.ui.theme.brightBlue
import com.example.raksaonlinecompose.ui.theme.darkBlue
import com.example.raksaonlinecompose.ui.theme.darkGray
import com.example.raksaonlinecompose.ui.theme.gray
import com.example.raksaonlinecompose.ui.theme.green
import com.example.raksaonlinecompose.ui.theme.nightDark
import com.example.raksaonlinecompose.ui.theme.orange
import com.example.raksaonlinecompose.ui.theme.purple
import com.example.raksaonlinecompose.ui.theme.redOrange
import com.example.raksaonlinecompose.ui.theme.white
import org.json.JSONArray


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

                val jsonString ="""
                    [{
		"type": "donutChart",
		"data": [{
				"label": "Tarik Tunai",
				"percentage": "55",
				"data": [{
					"trx_date": "21/01/2023",
					"nominal": 1000000
				}, {
					"trx_date": "20/01/2023",
					"nominal": 500000
				}, {
					"trx_date": "19/01/2023",
					"nominal": 1000000
				}]
			},
			{
				"label": "QRIS Payment",
				"percentage": "31",
				"data": [{
					"trx_date": "21/01/2023",
					"nominal": 159000
				}, {
					"trx_date": "20/01/2023",
					"nominal": 35000
				}, {
					"trx_date": "19/01/2023",
					"nominal": 1500
				}]
			},
			{
				"label": "Topup Gopay",
				"percentage": "7.7",
				"data": [{
					"trx_date": "21/01/2023",
					"nominal": 200000
				}, {
					"trx_date": "20/01/2023",
					"nominal": 195000
				}, {
					"trx_date": "19/01/2023",
					"nominal": 5000000
				}]
			},
			{
				"label": "Lainnya",
				"percentage": "6.3",
				"data": [{
					"trx_date": "21/01/2023",
					"nominal": 1000000
				}, {
					"trx_date": "20/01/2023",
					"nominal": 500000
				}, {
					"trx_date": "19/01/2023",
					"nominal": 1000000
				}]
			}
		]
	},
	{
		"type": "lineChart",
		"data": {
			"month": [3, 7, 8, 10, 5, 10, 1, 3, 5, 10, 7, 7]
		}
	}
]"""


                val listColor = listOf(
                    redOrange,
                    green,
                    blue,
                    brightBlue
                )

                val jsonArray = JSONArray(jsonString)

                val listDataTransaction: MutableList<PieChartInput> = mutableListOf()

                for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    val type = jsonObject.getString("type")

                    // If it's a "donutChart" type
                    if (type == "donutChart") {
                        val data = jsonObject.getJSONArray("data")
                        for (j in 0 until data.length()) {
                            val innerJsonObject = data.getJSONObject(j)
                            val label = innerJsonObject.getString("label")
                            val percentage = innerJsonObject.getString("percentage")
                            val colorList = listColor[j]

                            val detailTransactionList: MutableList<DetailTransaction> = mutableListOf()

                            val innerData = innerJsonObject.getJSONArray("data")
                            for (k in 0 until innerData.length()) {
                                val innerDataObject = innerData.getJSONObject(k)
                                val trxDate = innerDataObject.getString("trx_date")
                                val nominal = innerDataObject.getInt("nominal")

                                detailTransactionList.add(DetailTransaction(trxDate, nominal.toString()))
                            }

                            listDataTransaction.add(
                                PieChartInput(
                                    color = colorList,
                                    value = percentage.toFloat().toInt(),
                                    valueF = percentage.toFloat(),
                                    description = label,
                                    detailTransaction = detailTransactionList
                                )
                            )
                        }
                    }
                }




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
                        HomeScreen(navController,db,listDataTransaction)
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


