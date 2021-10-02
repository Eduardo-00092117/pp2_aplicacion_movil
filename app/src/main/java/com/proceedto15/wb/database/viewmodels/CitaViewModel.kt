package com.proceedto15.wb.database.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.proceedto15.wb.database.RoomDB
import com.proceedto15.wb.database.entities.*
import com.proceedto15.wb.database.repositories.CitaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CitaViewModel(private val app: Application) : AndroidViewModel(app){

    private val repository: CitaRepository
    val allCita: LiveData<List<Cita>>
    val allCitaXServicio: LiveData<List<CitaXServicio>>
    val allServicio: LiveData<List<Servicio>>
    val allCategoriaServicio: LiveData<List<CategoriaServicio>>
    val allCliente: LiveData<List<Cliente>>
    val allUsuario: LiveData<List<Usuario>>
    val allEmpleado: LiveData<List<Empleado>>
    val allEmpleadoXServicio: LiveData<List<EmpleadoXServicio>>

    init {
        val citaDAO = RoomDB.getDatabase(app).citaDAO()
        val citaXServicioDAO = RoomDB.getDatabase(app).citaXServicioDAO()
        val servicioDAO = RoomDB.getDatabase(app).servicioDAO()
        val categoriaServicioDAO = RoomDB.getDatabase(app).categoriaServicioDAO()
        val clienteDAO = RoomDB.getDatabase(app).clienteDAO()
        val usuarioDAO = RoomDB.getDatabase(app).usuarioDAO()
        val empleadoDAO = RoomDB.getDatabase(app).empleadoDAO()
        val empleadoXServicioDAO = RoomDB.getDatabase(app).empleadoXServicioDAO()

        repository = CitaRepository(
            citaDAO,
            citaXServicioDAO,
            servicioDAO,
            categoriaServicioDAO,
            clienteDAO,
            usuarioDAO,
            empleadoDAO,
            empleadoXServicioDAO
        )

        allCita = repository.allCita
        allCitaXServicio = repository.allCitaXServicio
        allServicio = repository.allServicio
        allCategoriaServicio = repository.allCategoriaServicio
        allCliente = repository.allCliente
        allUsuario = repository.allUsuario
        allEmpleado = repository.allEmpleado
        allEmpleadoXServicio = repository.allEmpleadoXServicio
    }

    // INSERTS

    fun insertCita(cita: Cita) = viewModelScope.launch(Dispatchers.IO){
        repository.insertCita(cita)
    }
    fun insertCitaXServicio(citaXServicio: CitaXServicio) = viewModelScope.launch(Dispatchers.IO){
        repository.insertCitaXServicio(citaXServicio)
    }
    fun insertServicio(servicio: Servicio) = viewModelScope.launch(Dispatchers.IO){
        repository.insertServicio(servicio)
    }
    fun insertCategoriaServicio(categoriaServicio: CategoriaServicio) = viewModelScope.launch(Dispatchers.IO){
        repository.insertCategoriaServicio(categoriaServicio)
    }
    fun insertCliente(cliente: Cliente) = viewModelScope.launch(Dispatchers.IO){
        repository.insertCliente(cliente)
    }
    fun insertUsuario(usuario: Usuario) = viewModelScope.launch(Dispatchers.IO){
        repository.insertUsuario(usuario)
    }
    fun insertEmpleado(empleado: Empleado) = viewModelScope.launch(Dispatchers.IO){
        repository.insertEmpleado(empleado)
    }
    fun insertEmpleadoXServicio(empleadoXServicio: EmpleadoXServicio) = viewModelScope.launch(Dispatchers.IO){
        repository.insertEmpleadoXServicio(empleadoXServicio)
    }

    // GETs

    fun getCita(id: Int) = repository.getCita(id)

    fun getCitaXServicio(id: Int) = repository.getCitaXServicio(id)

    fun getServicio(id: Int) = repository.getServicio(id)

    fun getCategoriaServicio(id: Int) = repository.getCategoriaServicio(id)

    fun getCliente(id: Int) = repository.getCliente(id)

    fun getUsuario(id: Int) = repository.getUsuario(id)

    fun getUsuarioPorEmail(email: String) = repository.getUsuarioPorEmail(email)

    fun getEmpleado(id: Int) = repository.getEmpleado(id)

    fun getEmpleadoXServicio(id: Int) = repository.getEmpleadoXServicio(id)

    // NukeTables

    private suspend fun nukeCita() = repository.nukeCita()

    private suspend fun nukeCitaXServicio() = repository.nukeCitaXServicio()

    private suspend fun nukeServicio() = repository.nukeServicio()

    private suspend fun nukeCategoriaServicio() = repository.nukeCategoriaServicio()

    private suspend fun nukeCliente() = repository.nukeCliente()

    private suspend fun nukeUsuario() = repository.nukeUsuario()

    private suspend fun nukeEmpleado() = repository.nukeEmpleado()

    private suspend fun nukeEmpleadoXServicio() = repository.nukeEmpleadoXServicio()
}