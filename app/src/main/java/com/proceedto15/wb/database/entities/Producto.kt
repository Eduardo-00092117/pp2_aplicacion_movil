package com.proceedto15.wb.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "producto")
data class Producto (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val idCategoria: Int,
    val idMarca: Int,
    val nombre: String,
    val precio: Float,
    val descripcion: String,
    val existencia: Int
)