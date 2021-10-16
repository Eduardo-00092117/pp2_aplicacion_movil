package com.proceedto15.wb.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "servicio")
data class Servicio (
    @PrimaryKey
    val id: Int,
    val idCategoria: Int,
    val nombre: String,
    val duracion_aprox: Int,
    val descripcion: String,
)