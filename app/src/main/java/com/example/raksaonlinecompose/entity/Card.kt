package com.example.raksaonlinecompose.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card")
data class Card(
    @PrimaryKey(
        autoGenerate = true
    )
    val id: Int? = null,
    val noCard: String,
    val amount: Long,
)