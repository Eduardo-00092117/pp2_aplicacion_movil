package com.proceedto15.wb.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pago")
data class Pago (
    @PrimaryKey
    val idPago: Int,
    val idOrden: Int,
    val metodo_pago: Boolean,
    val monto: Float,
    val nombre_titular: String,
    val numero: String,
    val fecha: String,
    val pin: Int
)