package com.proceedto15.wb.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "marca")
data class Marca (
    @PrimaryKey
    val idMarca: Int,
    val nombre: String,
    val descripcion: String
)