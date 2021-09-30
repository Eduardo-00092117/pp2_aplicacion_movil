package com.proceedto15.wb.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.proceedto15.wb.database.entities.Cita

@Dao
interface CitaDAO {

    @Insert
    suspend fun insert(cita: Cita)

    @Query("SELECT * FROM cita")
    fun getAllCita() : LiveData<List<Cita>>

    @Query("SELECT * FROM cita WHERE id = :id")
    fun getCita(id: Int): LiveData<Cita>

    @Query("DELETE FROM cita")
    suspend fun nukeTable()
}