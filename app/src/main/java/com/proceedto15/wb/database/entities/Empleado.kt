package com.proceedto15.wb.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "empleado")
data class Empleado (
    @PrimaryKey
    val idEmpleado: Int,
    val idUsuario: Int,
    val nombre: String,
    val apellido: String,
    val tipo_empleado: Boolean
)