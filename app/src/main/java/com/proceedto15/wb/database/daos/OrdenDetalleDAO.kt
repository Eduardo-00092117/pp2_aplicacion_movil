package com.proceedto15.wb.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.proceedto15.wb.database.entities.OrdenDetalle

@Dao
interface OrdenDetalleDAO {

    @Insert
    suspend fun insert(ordenDetalle: OrdenDetalle)

    @Query("SELECT * FROM orden_detalle")
    fun getAllOrdenDetalle(): LiveData<List<OrdenDetalle>>

    @Query("SELECT * FROM orden_detalle WHERE id = :id")
    fun getOrdenDetalle(id: Int): LiveData<OrdenDetalle>

    @Query("DELETE FROM orden_detalle")
    suspend fun nukeTable()
}