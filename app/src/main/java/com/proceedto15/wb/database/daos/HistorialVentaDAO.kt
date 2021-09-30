package com.proceedto15.wb.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.proceedto15.wb.database.entities.HistorialVenta

@Dao
interface HistorialVentaDAO {

    @Insert
    suspend fun insert(historialVenta: HistorialVenta)

    @Query("SELECT * FROM historial_venta")
    fun getAllHistorialVenta() : LiveData<List<HistorialVenta>>

    @Query("SELECT * FROM historial_venta WHERE id = :id")
    fun getHistorialVenta(id: Int): LiveData<HistorialVenta>

    @Query("DELETE FROM historial_venta")
    suspend fun nukeTable()
}