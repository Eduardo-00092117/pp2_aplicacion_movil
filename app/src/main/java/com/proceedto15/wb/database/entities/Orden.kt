package com.proceedto15.wb.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orden")
data class Orden (
    @PrimaryKey
    val id: Int,
    val idCliente: Int,
    val fecha: String,
    val hora: String
)