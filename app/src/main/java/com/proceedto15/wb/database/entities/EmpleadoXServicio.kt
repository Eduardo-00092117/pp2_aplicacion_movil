package com.proceedto15.wb.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "empleadoxservicio")
data class EmpleadoXServicio (
    @PrimaryKey
    val idEmpleado: Int,
    val idServicio: Int
)