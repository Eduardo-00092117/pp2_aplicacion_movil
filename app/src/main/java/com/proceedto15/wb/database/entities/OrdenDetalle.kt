package com.proceedto15.wb.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orden_detalle")
data class OrdenDetalle(
    @PrimaryKey
    val idOrdenDetalle: Int,
    val idProducto: Int,
    val idOrden: Int,
    val cantidad: Int,
    val total: Float
)