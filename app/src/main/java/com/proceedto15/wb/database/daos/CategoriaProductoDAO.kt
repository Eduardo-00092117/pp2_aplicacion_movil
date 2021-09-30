package com.proceedto15.wb.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.proceedto15.wb.database.entities.CategoriaProducto

@Dao
interface CategoriaProductoDAO {

    @Insert
    suspend fun insert(categoriaProducto: CategoriaProducto)

    @Query("SELECT * FROM categoria_producto")
    fun getAllCategoriaProducto() : LiveData<List<CategoriaProducto>>

    @Query("SELECT * FROM categoria_producto WHERE id = :id")
    fun getCategoriaProducto(id: Int): LiveData<CategoriaProducto>

    @Query("DELETE FROM categoria_producto")
    suspend fun nukeTable()
}