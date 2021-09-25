package com.proceedto15.wb.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.proceedto15.wb.database.entities.Cliente

@Dao
interface ClienteDAO {

    @Insert
    suspend fun insert(cliente: Cliente)

    @Query("SELECT * FROM cliente")
    fun getAllCliente() : LiveData<List<Cliente>>

    @Query("SELECT * FROM cliente WHERE idCliente = :id")
    fun getCliente(id: Int): LiveData<Cliente>

    @Query("DELETE FROM cliente")
    suspend fun nukeTable()
}