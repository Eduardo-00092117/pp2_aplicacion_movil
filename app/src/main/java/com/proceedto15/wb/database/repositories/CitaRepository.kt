package com.proceedto15.wb.database.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.proceedto15.wb.database.daos.*
import com.proceedto15.wb.database.entities.*

class CitaRepository (
    private val citaDAO: CitaDAO,
    private val citaXServicioDAO: CitaXServicioDAO,
    private val servicioDAO: ServicioDAO,
    private val categoriaServicioDAO: CategoriaServicioDAO,
    private val clienteDAO: ClienteDAO,
    private val usuarioDAO: UsuarioDAO,
    private val empleadoDAO: EmpleadoDAO,
    private val empleadoXServicioDAO: EmpleadoXServicioDAO) {

    val allCita: LiveData<List<Cita>> = citaDAO.getAllCita()
    val allCitaXServicio: LiveData<List<CitaXServicio>> = citaXServicioDAO.getAllCitaXServicio()
    val allServicio: LiveData<List<Servicio>> = servicioDAO.getAllServicio()
    val allCategoriaServicio: LiveData<List<CategoriaServicio>> = categoriaServicioDAO.getAllCategoriaServicio()
    val allCliente: LiveData<List<Cliente>> = clienteDAO.getAllCliente()
    val allUsuario: LiveData<List<Usuario>> = usuarioDAO.getAllUsuario()
    val allEmpleado: LiveData<List<Empleado>> = empleadoDAO.getAllEmpleado()
    val allEmpleadoXServicio: LiveData<List<EmpleadoXServicio>> = empleadoXServicioDAO.getAllEmpleadoXServicio()

    // INSERTS

    @WorkerThread
    suspend fun insertCita(cita: Cita){
        citaDAO.insert(cita)
    }
    @WorkerThread
    suspend fun insertCitaXServicio(citaXServicio: CitaXServicio){
        citaXServicioDAO.insert(citaXServicio)
    }
    @WorkerThread
    suspend fun insertServicio(servicio: Servicio){
        servicioDAO.insert(servicio)
    }
    @WorkerThread
    suspend fun insertCategoriaServicio(categoriaServicio: CategoriaServicio){
        categoriaServicioDAO.insert(categoriaServicio)
    }
    @WorkerThread
    suspend fun insertCliente(cliente: Cliente){
        clienteDAO.insert(cliente)
    }
    @WorkerThread
    suspend fun insertUsuario(usuario: Usuario){
        usuarioDAO.insert(usuario)
    }
    @WorkerThread
    suspend fun insertEmpleado(empleado: Empleado){
        empleadoDAO.insert(empleado)
    }
    @WorkerThread
    suspend fun insertEmpleadoXServicio(empleadoXServicio: EmpleadoXServicio){
        empleadoXServicioDAO.insert(empleadoXServicio)
    }

    // GETs

    fun getCita(id: Int) = citaDAO.getCita(id)

    fun getCitaXServicio(id: Int) = citaXServicioDAO.getCitaXServicio(id)

    fun getServicio(id: Int) = servicioDAO.getServicio(id)

    fun getCategoriaServicio(id: Int) = categoriaServicioDAO.getCategoriaServicio(id)

    fun getCliente(id: Int) = clienteDAO.getCliente(id)

     fun getClienteByUser(idUser: Int) = clienteDAO.getClienteByUser(idUser)

    fun getUsuario(id: Int) = usuarioDAO.getUsuario(id)

     fun getUsuarioPorEmail(email: String) = usuarioDAO.getUsuarioPorEmail(email)

    fun getEmpleado(id: Int) = empleadoDAO.getEmpleado(id)

    fun getEmpleadoXServicio(id: Int) = empleadoXServicioDAO.getEmpleadoXServicio(id)

    fun getEmpleadosPorServicio(id: Int) = servicioDAO.getEmpleadosPorServicio(id)

    // Delete 1
    @WorkerThread
    suspend fun deleteOneCita(id: Int) = citaDAO.deleteOneCita(id)

    // NukeTables

    @WorkerThread
    suspend fun nukeCita(){
        return citaDAO.nukeTable()
    }
    @WorkerThread
    suspend fun nukeCitaXServicio(){
        return citaXServicioDAO.nukeTable()
    }
    @WorkerThread
    suspend fun nukeServicio(){
        return servicioDAO.nukeTable()
    }
    @WorkerThread
    suspend fun nukeCategoriaServicio(){
        return categoriaServicioDAO.nukeTable()
    }
    @WorkerThread
    suspend fun nukeCliente(){
        return clienteDAO.nukeTable()
    }
    @WorkerThread
    suspend fun nukeUsuario(){
        return usuarioDAO.nukeTable()
    }
    @WorkerThread
    suspend fun nukeEmpleado(){
        return empleadoDAO.nukeTable()
    }
    @WorkerThread
    suspend fun nukeEmpleadoXServicio(){
        return empleadoXServicioDAO.nukeTable()
    }
}