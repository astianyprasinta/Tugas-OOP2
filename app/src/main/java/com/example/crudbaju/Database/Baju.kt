package com.example.crudbaju.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "baju")
data class Baju(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "jenis") val jenis: String,
    @ColumnInfo(name = "stok") val stok: Int,
    @ColumnInfo(name = "harga") val harga: Int
)