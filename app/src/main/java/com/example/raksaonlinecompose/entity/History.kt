package com.example.raksaonlinecompose.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class History(
    @PrimaryKey(
        autoGenerate = true
    )
    val id: Int? = null,
    val noCard: String,
    val amount: Long,
    val newAmount: Long,
    val bankName: String,
    val idUser: String,
    val merchant: String,
    val value: Long,
    val status: String

)