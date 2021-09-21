package com.proceedto15.wb.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.proceedto15.wb.database.entities.Cliente
import com.proceedto15.wb.database.entities.HistorialVenta

@Dao
interface HistorialVentaDAO {

    @Insert
    suspend fun insert(historialVentaDAO: HistorialVentaDAO)

    @Query("SELECT * FROM historial_venta")
    fun getAllHistorialVenta() : LiveData<List<HistorialVenta>>

    @Query("SELECT * FROM historial_venta WHERE idHistorialVenta = :id")
    fun getHistorialVenta(id: Int): LiveData<HistorialVenta>

    @Query("DELETE FROM historial_venta")
    suspend fun nukeTable()
}