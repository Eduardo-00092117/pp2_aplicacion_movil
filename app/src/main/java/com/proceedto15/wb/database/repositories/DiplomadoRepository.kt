package com.proceedto15.wb.database.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.proceedto15.wb.database.daos.DiplomadoDAO
import com.proceedto15.wb.database.daos.ImpartidorDAO
import com.proceedto15.wb.database.entities.Diplomado
import com.proceedto15.wb.database.entities.Impartidor

class DiplomadoRepository (
    private val ImpartidorDAO: ImpartidorDAO,
    private val DiplomadoDAO: DiplomadoDAO) {

    val allImpartidor: LiveData<List<Impartidor>> = ImpartidorDAO.getAllImpartidor()
    val allDiplomado: LiveData<List<Diplomado>> = DiplomadoDAO.getAllDiplomado()

    //Inserts

    @WorkerThread
    suspend fun insertImpartidor(impartidor: Impartidor){
        ImpartidorDAO.insert(impartidor)
    }
    @WorkerThread
    suspend fun insertDiplomado(diplomado: Diplomado){
        DiplomadoDAO.insert(diplomado)
    }

    //GETs

    fun getImpartidor(id: Int) = ImpartidorDAO.getImpartidor(id)
    fun getDiplomado(id: Int) = DiplomadoDAO.getDiplomado(id)


    //Delete

    @WorkerThread
    suspend fun nukeImpartidor(){
        return ImpartidorDAO.nukeTable()
    }

    @WorkerThread
    suspend fun nukeDiplomado(){
        return DiplomadoDAO.nukeTable()
    }



}