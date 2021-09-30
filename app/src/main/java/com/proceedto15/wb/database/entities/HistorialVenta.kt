package com.proceedto15.wb.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "historial_venta")
data class HistorialVenta (
    @PrimaryKey
    val id: Int,
    val idCategoria: Int,
    val idMarca: Int,
    val idOrden: Int,
    val nombre: String,
    val precio: Float,
    val descripcion: String,
    val cantidad: Int,
    val total: Float
)