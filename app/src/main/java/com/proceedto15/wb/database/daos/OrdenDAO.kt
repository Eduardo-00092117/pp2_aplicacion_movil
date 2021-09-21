package com.proceedto15.wb.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.proceedto15.wb.database.entities.Orden

@Dao
interface OrdenDAO {

    @Insert
    suspend fun insert(orden: Orden)

    @Query("SELECT * FROM orden")
    fun getAllOrden(): LiveData<List<Orden>>

    @Query("SELECT * FROM orden WHERE idOrden = :id")
    fun getOrden(id: Int): LiveData<Orden>

    @Query("DELETE FROM orden")
    suspend fun nukeTable()
}