package com.proceedto15.wb.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cliente")
data class Cliente (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val idUsuario: Int,
    val nombre: String,
    val apellido: String,
    val fecha_nacimiento: String
)