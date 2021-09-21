package com.proceedto15.wb.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.proceedto15.wb.database.entities.Marca

@Dao
interface MarcaDAO {

    @Insert
    suspend fun insert(marca: Marca)

    @Query("SELECT * FROM marca")
    fun getAllMarca(): LiveData<List<Marca>>

    @Query("SELECT * FROM marca WHERE idMarca = :id")
    fun getMarca(id: Int): LiveData<Marca>

    @Query("DELETE FROM marca")
    suspend fun nukeTable()
}