package com.proceedto15.wb.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categoria_servicio")
data class CategoriaServicio (
    @PrimaryKey
    val idCategoria: Int,
    val nombre: String,
    val descripcion: String
)