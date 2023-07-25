package com.example.raksaonlinecompose.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.raksaonlinecompose.entity.Card

@Dao
interface CardDao {

    @Query("Select * from card")
    fun gelAllCard(): List<Card>

    @Query("INSERT OR REPLACE INTO card (noCard, amount) VALUES (:noCard, :amount)")
    fun insertOrReplaceUser(noCard:String, amount:Long)

    @Query("DELETE FROM card")
    fun deleteAllCard()

    @Query("UPDATE card SET amount = :newAmount WHERE noCard = :cardNo")
    fun updateAmountByCardNo(cardNo: String, newAmount: Long)


}