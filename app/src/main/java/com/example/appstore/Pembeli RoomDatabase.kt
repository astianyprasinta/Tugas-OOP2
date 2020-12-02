package com.UAS.apps

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Pembeli::class), version = 1)
abstract class PembeliRoomDatabase : RoomDatabase() {
    abstract fun Pembeliwrg(): Pembeliwrg

    abstract val applicationContext: Context
    val db = Room.databaseBuilder(
            applicationContext,
            PembeliRoomDatabase::class.java, "DBMHS"
    ).build()

}