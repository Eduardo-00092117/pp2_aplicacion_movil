package com.proceedto15.wb.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.proceedto15.wb.database.entities.Cliente
import com.proceedto15.wb.database.entities.Diplomado

@Dao
interface DiplomadoDAO {

    @Insert
    suspend fun insert(diplomado: Diplomado)

    @Query("SELECT * FROM diplomado")
    fun getAllDiplomado() : LiveData<List<Diplomado>>

    @Query("SELECT * FROM diplomado WHERE idDiplomado = :id")
    fun getDiplomado(id: Int): LiveData<Diplomado>

    @Query("DELETE FROM diplomado")
    suspend fun nukeTable()
}