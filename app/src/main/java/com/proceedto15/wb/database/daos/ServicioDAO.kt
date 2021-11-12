package com.proceedto15.wb.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.proceedto15.wb.database.entities.Empleado
import com.proceedto15.wb.database.entities.Servicio

@Dao
interface ServicioDAO {

    @Insert
    suspend fun insert(servicio: Servicio)

    @Query("SELECT * FROM servicio")
    fun getAllServicio(): LiveData<List<Servicio>>

    @Query("SELECT * FROM servicio WHERE id = :id")
    fun getServicio(id: Int): LiveData<Servicio>

    @Query("SELECT e.id, e.idUsuario, e.nombre, e.apellido, e.tipo_empleado FROM empleado e INNER JOIN empleadoxservicio es ON e.id = es.idEmpleado INNER JOIN servicio s ON es.idServicio = s.id WHERE s.id = :id")
    fun getEmpleadosPorServicio(id: Int): LiveData<List<Empleado>>

    @Query("DELETE FROM servicio")
    suspend fun nukeTable()
}