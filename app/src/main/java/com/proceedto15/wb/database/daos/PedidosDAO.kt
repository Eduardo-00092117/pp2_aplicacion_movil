package com.proceedto15.wb.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.proceedto15.wb.database.entities.Pedidos
import com.proceedto15.wb.database.entities.Producto

@Dao
interface PedidosDAO {

    @Insert
    suspend fun insert(pedido: Pedidos)

    @Query("SELECT * FROM pedidos")
    fun getAllPedidos(): LiveData<List<Pedidos>>

    @Query("SELECT * FROM pedidos WHERE id = :id")
    fun getPedido(id: Int): LiveData<Pedidos>

    @Query("DELETE FROM pedidos WHERE id = :id")
    suspend fun deleteOnePedido(id: Int)

    @Query("DELETE FROM pedidos")
    suspend fun deleteAllPedido()

    @Query("DELETE FROM pedidos")
    suspend fun nukeTable()

    @Query("UPDATE pedidos SET Qty = Qty + :amount WHERE id = :id")
    suspend fun updatePedido(id: Int, amount: Int)
}