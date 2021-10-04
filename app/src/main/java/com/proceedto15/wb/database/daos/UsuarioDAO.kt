package com.proceedto15.wb.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.proceedto15.wb.database.entities.Usuario

@Dao
interface UsuarioDAO {

    @Insert
    suspend fun insert(usuario: Usuario)

    @Query("SELECT * FROM usuario")
    fun getAllUsuario(): LiveData<List<Usuario>>

    @Query("SELECT * FROM usuario WHERE id = :id")
    fun getUsuario(id: Int): LiveData<Usuario>

    @Query("SELECT * FROM usuario WHERE usuario = :usuario")
    fun getUsuarioPorEmail(usuario: String): LiveData<Usuario>

    @Query("DELETE FROM usuario")
    suspend fun nukeTable()
}