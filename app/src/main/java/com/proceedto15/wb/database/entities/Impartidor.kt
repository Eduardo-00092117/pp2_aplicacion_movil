package com.proceedto15.wb.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "impartidor")
data class Impartidor (
    @PrimaryKey
    val id: Int,
    val nombre: String,
    val apellido: String
)
