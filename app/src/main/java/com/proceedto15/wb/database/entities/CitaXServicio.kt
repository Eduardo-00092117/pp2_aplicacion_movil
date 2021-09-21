package com.proceedto15.wb.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "citaxservicio")
data class CitaXServicio (
    @PrimaryKey
    val idCitaServicio: Int,
    val idCita: Int,
    val idServicio: Int
)