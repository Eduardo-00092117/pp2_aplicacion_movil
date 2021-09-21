package com.proceedto15.wb.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.proceedto15.wb.database.entities.Impartidor

@Dao
interface ImpartidorDAO {

    @Insert
    suspend fun insert(impartidor: Impartidor)

    @Query("SELECT * FROM impartidor")
    fun getAllImpartidor(): LiveData<List<Impartidor>>

    @Query("SELECT * FROM impartidor WHERE idImpartidor = :id")
    fun getImpartidor(id: Int): LiveData<Impartidor>

    @Query("DELETE FROM impartidor")
    suspend fun nukeTable()
}