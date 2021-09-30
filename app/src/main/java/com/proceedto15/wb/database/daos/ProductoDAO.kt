package com.proceedto15.wb.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.proceedto15.wb.database.entities.Producto

@Dao
interface ProductoDAO {

    @Insert
    suspend fun insert(producto: Producto)

    @Query("SELECT * FROM producto")
    fun getAllProducto(): LiveData<List<Producto>>

    @Query("SELECT * FROM producto WHERE id = :id")
    fun getProducto(id: Int): LiveData<Producto>

    @Query("DELETE FROM producto")
    suspend fun nukeTable()
}