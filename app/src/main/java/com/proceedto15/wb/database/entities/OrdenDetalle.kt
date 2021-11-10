package com.proceedto15.wb.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orden_detalle")
data class OrdenDetalle(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val idProducto: Int,
    val idOrden: Int,
    val cantidad: Int,
    val costo_unitario: Float,
    val total: Float
)