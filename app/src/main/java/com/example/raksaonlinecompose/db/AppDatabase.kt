package com.example.raksaonlinecompose.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.raksaonlinecompose.dao.CardDao
import com.example.raksaonlinecompose.dao.HistoryDao
import com.example.raksaonlinecompose.entity.Card
import com.example.raksaonlinecompose.entity.History

@Database(
    entities =
    [
    Card::class,
    History::class
    ],
    version = 2, exportSchema = false)
//@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cardDao() : CardDao

    abstract fun historyDao(): HistoryDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        val migration1_2: Migration = object : Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE IF NOT EXISTS history (\n" +
                        "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                        "    noCard TEXT,\n" +
                        "    amount INTEGER,\n" +
                        "    newAmount INTEGER,\n" +
                        "    bankName TEXT,\n" +
                        "    idUser TEXT,\n" +
                        "    merchant TEXT,\n" +
                        "    value INTEGER,\n" +
                        "    status TEXT\n" +
                        ")")
            }

        }

        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "user.db").allowMainThreadQueries()
                        .addMigrations(migration1_2)
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}