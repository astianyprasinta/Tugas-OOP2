package com.UAS.apps

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

interface Pembeliwrg {
    @Query("SELECT * FROM pembeli")
    fun getAll(): List<Pembeli>

    @Query("SELECT * FROM pembeli WHERE id_pembeli IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Pembeli>

    @Query("SELECT * FROM pembeli WHERE nama_pembeli LIKE :nama_pembeli AND " +
            "alamat LIKE :divisi LIMIT 1")
    fun findByName(nama: String, alamat: String): Pembeli

    @Insert
    fun insertAll(vararg Pembeli: Pembeli)

    @Delete
    fun delete(pembeli: Pembeli)
}
