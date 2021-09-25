package com.proceedto15.wb.database.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.proceedto15.wb.database.RoomDB
import com.proceedto15.wb.database.daos.DiplomadoDAO
import com.proceedto15.wb.database.daos.ImpartidorDAO
import com.proceedto15.wb.database.entities.Diplomado
import com.proceedto15.wb.database.entities.Impartidor
import com.proceedto15.wb.database.repositories.DiplomadoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DiplomadoViewModel(private val app: Application) : AndroidViewModel(app){
    private val repository: DiplomadoRepository
    val allImpartidor: LiveData<List<Impartidor>>
    val allDiplomado: LiveData<List<Diplomado>>

    init {
        val ImpartidorDAO = RoomDB.getDatabase(app).ImpartidorDAO()
        val DiplomadoDAO = RoomDB.getDatabase(app).DiplomadoDAO()

        repository = DiplomadoRepository(ImpartidorDAO, DiplomadoDAO)
        allImpartidor = repository.allImpartidor
        allDiplomado = repository.allDiplomado
    }

    //Inserts
    fun insertImpartidor(impartidor: Impartidor) = viewModelScope.launch(Dispatchers.IO){
        repository.insertImpartidor(impartidor)
    }
    fun insertDiplomado(diplomado: Diplomado) = viewModelScope.launch(Dispatchers.IO){
        repository.insertDiplomado(diplomado)
    }

    //GET

    fun getImpartidor(id: Int) = repository.getImpartidor(id)

    fun getDiplomado(id: Int) = repository.getDiplomado(id)

    //Delete

    private suspend fun nukeImpartidor() = repository.nukeImpartidor()

    private suspend fun nukeDiplomado() = repository.nukeDiplomado()
}