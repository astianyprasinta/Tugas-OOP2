package com.example.uasoop2.Database

import androidx.room.*

@Dao
interface PembeliDao {
    @Insert
    suspend fun addPembeli(pembeli: Pembeli)

    @Update
    suspend fun updatePembeli(pembeli: Pembeli)

    @Delete
    suspend fun deletePembeli(pembeli: Pembeli)

    @Query("SELECT * FROM pembeli")
    suspend fun getAllPembeli(): List<Pembeli>

    @Query("SELECT * FROM pembeli WHERE id=:pembeli_id")
    suspend fun getPembeli(pembeli_id: Int) : List<Pembeli>

}