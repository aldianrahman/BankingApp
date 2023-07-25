package com.example.raksaonlinecompose.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.raksaonlinecompose.entity.Card
import com.example.raksaonlinecompose.entity.History

@Dao
interface HistoryDao {

    @Query("Select * from history")
    fun gelAllHistory(): List<History>

    @Query("INSERT OR REPLACE INTO history (noCard, amount,bankName,idUser,merchant,newAmount,value,status) VALUES (:noCard, :amount,:bankName, :idUser,:merchant,:newAmount,:value,:status)")
    fun insertOrReplaceUser(noCard: String, amount:Long,bankName:String,idUser: String,merchant: String ,newAmount:Long,value:Long,status:String)

}