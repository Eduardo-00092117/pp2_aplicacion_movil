package com.proceedto15.wb.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cita")
data class Cita (
    @PrimaryKey
    val idCita: Int,
    val idCliente: Int,
    val fecha: String,
    val hora: String
)