package com.example.uasoop2.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pembeli")
data class Pembeli(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "merk") val merk: String,
    @ColumnInfo(name = "stok") val stok: Int,
    @ColumnInfo(name = "harga") val harga: Int
)