package com.example.raksaonlinecompose.model

import androidx.compose.ui.graphics.Color

data class PieChartInput(
    val color: Color= Color.White,
    var value:Int,
    var valueF:Float,
    var description:String,
    var isTapped:Boolean= false,
    var onDetail: Boolean = false,
    var detailTransaction: List<DetailTransaction>? = null
)

data class DetailTransaction(
    val trx_date: String,
    val nominal: String
)