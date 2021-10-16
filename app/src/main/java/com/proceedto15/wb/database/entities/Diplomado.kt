package com.proceedto15.wb.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "diplomado")
data class Diplomado (
    @PrimaryKey
    val id: Int,
    val idImpartidor: Int,
    val nombre: String,
    val lugar: String,
    val cupo: Int,
    val fecha_inicio: String,
    val fecha_fin: String,
    val duracion: String,
    val precio: Float,
    val descripcion: String,
    val cantidad: Int,
    val total: Float
)