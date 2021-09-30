package com.proceedto15.wb.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuario")
data class Usuario (
    @PrimaryKey
    val id: Int,
    val usuario: String,
    val contrasenia: String
)