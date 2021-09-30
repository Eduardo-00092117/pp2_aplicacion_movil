package com.proceedto15.wb.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.proceedto15.wb.database.entities.Empleado

@Dao
interface EmpleadoDAO {

    @Insert
    suspend fun insert(empleado: Empleado)

    @Query("SELECT * FROM empleado")
    fun getAllEmpleado() : LiveData<List<Empleado>>

    @Query("SELECT * FROM empleado WHERE id = :id")
    fun getEmpleado(id: Int): LiveData<Empleado>

    @Query("DELETE FROM empleado")
    suspend fun nukeTable()
}