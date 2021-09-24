package com.proceedto15.wb.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "servicio")
data class Servicio (
    @PrimaryKey
    val idServicio: Int,
    val idCategoria: Int,
    val nombre: String,
    val duracion_aprox: Float,
    val descripcion: String
)