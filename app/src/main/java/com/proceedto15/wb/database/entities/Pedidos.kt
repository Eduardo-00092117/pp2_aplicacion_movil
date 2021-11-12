package com.proceedto15.wb.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pedidos")
data class Pedidos (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val PName: String,
    val Qty: Int,
    val UnitPrice: Float,
    val TotalPrice: Float

    )