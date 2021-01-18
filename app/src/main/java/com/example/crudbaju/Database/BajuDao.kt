package com.example.crudbaju.Database

import androidx.room.*

@Dao
interface BajuDao {
    @Insert
    suspend fun addBaju(baju: Baju)

    @Update
    suspend fun updateBaju(baju: Baju)

    @Delete
    suspend fun deleteBaju(baju: Baju)

    @Query("SELECT * FROM baju")
    suspend fun getAllBaju(): List<Baju>

    @Query("SELECT * FROM baju WHERE id=:baju_id")
    suspend fun getBaju(baju_id: Int) : List<Baju>

}