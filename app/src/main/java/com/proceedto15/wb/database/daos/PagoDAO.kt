package com.proceedto15.wb.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.proceedto15.wb.database.entities.Pago

@Dao
interface PagoDAO {

    @Insert
    suspend fun insert(pago: Pago)

    @Query("SELECT * FROM pago")
    fun getAllPago(): LiveData<List<Pago>>

    @Query("SELECT * FROM pago WHERE idPago = :id")
    fun getPago(id: Int): LiveData<Pago>

    @Query("DELETE FROM pago")
    suspend fun nukeTable()
}