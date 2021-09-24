package com.proceedto15.wb.database.repositories

import com.proceedto15.wb.database.daos.*

class CitaRepository (
    private val CitaDAO: CitaDAO,
    private val CitaXServicioDAO: CitaXServicioDAO,
    private val ServicioDAO: ServicioDAO,
    private val CategoriaServicioDAO: CategoriaServicioDAO,
    private val ClienteDAO: ClienteDAO,
    private val UsuarioDAO: UsuarioDAO,
    private val EmpleadoDAO: EmpleadoDAO,
    private val EmpleadoXServicioDAO: EmpleadoXServicioDAO) {

}