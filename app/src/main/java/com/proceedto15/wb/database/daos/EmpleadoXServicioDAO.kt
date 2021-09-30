package com.proceedto15.wb.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.proceedto15.wb.database.entities.EmpleadoXServicio

@Dao
interface EmpleadoXServicioDAO {

    @Insert
    suspend fun insert(empleadoXServicio: EmpleadoXServicio)

    @Query("SELECT * FROM empleadoxservicio")
    fun getAllEmpleadoXServicio() : LiveData<List<EmpleadoXServicio>>

    @Query("SELECT * FROM empleadoxservicio WHERE id = :id")
    fun getEmpleadoXServicio(id: Int): LiveData<EmpleadoXServicio>

    @Query("DELETE FROM empleadoxservicio")
    suspend fun nukeTable()
}