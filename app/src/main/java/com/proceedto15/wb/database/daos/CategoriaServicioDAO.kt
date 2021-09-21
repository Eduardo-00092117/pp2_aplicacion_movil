package com.proceedto15.wb.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.proceedto15.wb.database.entities.CategoriaServicio

@Dao
interface CategoriaServicioDAO {

    @Insert
    suspend fun insert(categoriaServicio: CategoriaServicio)

    @Query("SELECT * FROM categoria_servicio")
    fun getAllCategoriaServicio() : LiveData<List<CategoriaServicio>>

    @Query("SELECT * FROM categoria_servicio WHERE idCategoria = :id")
    fun getCategoriaServicio(id: Int): LiveData<CategoriaServicio>

    @Query("DELETE FROM categoria_servicio")
    suspend fun nukeTable()
}