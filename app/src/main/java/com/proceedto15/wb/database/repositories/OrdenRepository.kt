package com.proceedto15.wb.database.repositories

import com.proceedto15.wb.database.daos.*

class OrdenRepository (
    private val PagoDAO: PagoDAO,
    private val OrdenDAO: OrdenDAO,
    private val OrdenDetalleDAO: OrdenDetalleDAO,
    private val ProductoDAO: ProductoDAO,
    private val CategoriaProductoDAO: CategoriaProductoDAO,
    private val MarcaDAO: MarcaDAO,
    private val HistorialVentaDAO: HistorialVentaDAO) {
}