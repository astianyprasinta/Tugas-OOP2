package com.UAS.apps

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.w3c.dom.Text
import java.text.DecimalFormat

@Entity(tableName = "pembeli")
data class Pembeli (
        @PrimaryKey val id_pembeli: Int,
        @ColumnInfo(name = "nama_pembeli") val nama_pembeli: Text?,
        @ColumnInfo(name = "alamat") val alamat : Text?
)