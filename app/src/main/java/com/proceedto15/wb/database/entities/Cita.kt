package com.proceedto15.wb.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "cita")
data class Cita (
    @PrimaryKey
    val id: Int,
    val idCliente: Int,
    val fecha: String,
    val hora: String
) : Serializable