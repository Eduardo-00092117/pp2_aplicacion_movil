package com.proceedto15.wb.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.proceedto15.wb.database.entities.CitaXServicio

@Dao
interface CitaXServicioDAO {

    @Insert
    suspend fun insert(citaXServicio: CitaXServicio)

    @Query("SELECT * FROM citaxservicio")
    fun getAllCitaXServicio() : LiveData<List<CitaXServicio>>

    @Query("SELECT * FROM citaxservicio WHERE id = :id")
    fun getCitaXServicio(id: Int): LiveData<CitaXServicio>

    @Query("DELETE FROM citaxservicio")
    suspend fun nukeTable()
}