package com.example.uasoop2.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Pembeli::class, Admin::class), version = 1)

abstract class AppRoomDB : RoomDatabase() {

    abstract fun pembeliDao(): PembeliDao
    abstract fun adminDao(): AdminDao

    companion object {

        @Volatile
        private var INSTANCE: AppRoomDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK){
            INSTANCE ?: buildDatabase(context).also {
                INSTANCE = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppRoomDB::class.java,
            "APPDB"
        ).build()

    }
}