package com.proceedto15.wb.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categoria_producto")
data class CategoriaProducto (
    @PrimaryKey
    val idCategoriaProducto: Int,
    val nombre: String,
    val descripcion: String
)