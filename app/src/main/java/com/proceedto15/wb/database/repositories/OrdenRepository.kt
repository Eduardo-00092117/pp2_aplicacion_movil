package com.proceedto15.wb.database.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.proceedto15.wb.database.daos.*
import com.proceedto15.wb.database.entities.*

class OrdenRepository (
    private val PagoDAO: PagoDAO,
    private val OrdenDAO: OrdenDAO,
    private val OrdenDetalleDAO: OrdenDetalleDAO,
    private val ProductoDAO: ProductoDAO,
    private val CategoriaProductoDAO: CategoriaProductoDAO,
    private val PedidosDAO: PedidosDAO,
    private val MarcaDAO: MarcaDAO,
    private val HistorialVentaDAO: HistorialVentaDAO) {

    val allPago: LiveData<List<Pago>> = PagoDAO.getAllPago()
    val allOrden: LiveData<List<Orden>> = OrdenDAO.getAllOrden()
    val allOrdenDetalle: LiveData<List<OrdenDetalle>> = OrdenDetalleDAO.getAllOrdenDetalle()
    val allProducto: LiveData<List<Producto>> = ProductoDAO.getAllProducto()
    val allCategoriaProducto: LiveData<List<CategoriaProducto>> = CategoriaProductoDAO.getAllCategoriaProducto()
    val allMarca: LiveData<List<Marca>> = MarcaDAO.getAllMarca()
    val allHistorialVenta: LiveData<List<HistorialVenta>> = HistorialVentaDAO.getAllHistorialVenta()
    val allPedidos: LiveData<List<Pedidos>> = PedidosDAO.getAllPedidos()

    // INSERTS

    @WorkerThread
    suspend fun insertPago(pago: Pago){
        PagoDAO.insert(pago)
    }
    @WorkerThread
    suspend fun insertOrden(orden: Orden){
        OrdenDAO.insert(orden)
    }
    @WorkerThread
    suspend fun insertOrdenDetalle(ordenDetalle: OrdenDetalle){
        OrdenDetalleDAO.insert(ordenDetalle)
    }
    @WorkerThread
    suspend fun insertProducto(producto: Producto){
        ProductoDAO.insert(producto)
    }
    @WorkerThread
    suspend fun insertPedidos(pedidos: Pedidos){
        PedidosDAO.insert(pedidos)
    }
    @WorkerThread
    suspend fun insertCategoriaProducto(categoriaProducto: CategoriaProducto){
        CategoriaProductoDAO.insert(categoriaProducto)
    }
    @WorkerThread
    suspend fun insertMarca(marca: Marca){
        MarcaDAO.insert(marca)
    }
    @WorkerThread
    suspend fun insertHistorialVenta(historialVenta: HistorialVenta){
        HistorialVentaDAO.insert(historialVenta)
    }

    // GETs

    fun getPago(id: Int) = PagoDAO.getPago(id)

    fun getOrden(id: Int) = OrdenDAO.getOrden(id)

    fun getOrdenDetalle(id: Int) = OrdenDetalleDAO.getOrdenDetalle(id)

    fun getProducto(id: Int) = ProductoDAO.getProducto(id)

    fun getCategoriaProducto(id: Int) = CategoriaProductoDAO.getCategoriaProducto(id)

    fun getMarca(id: Int) = MarcaDAO.getMarca(id)

    fun getHistorialVenta(id: Int) = HistorialVentaDAO.getHistorialVenta(id)

    fun getPedido(id: Int) = PedidosDAO.getPedido(id)

    // DELETES

    @WorkerThread
    suspend fun deleteOnePedido(id: Int) = PedidosDAO.deleteOnePedido(id)

    @WorkerThread
    suspend fun deleteAllPedido() = PedidosDAO.deleteAllPedido()

    // NukeTables

    @WorkerThread
    suspend fun nukePago(){
        return PagoDAO.nukeTable()
    }
    @WorkerThread
    suspend fun nukeOrden(){
        return OrdenDAO.nukeTable()
    }
    @WorkerThread
    suspend fun nukeOrdenDetalle(){
        return OrdenDetalleDAO.nukeTable()
    }
    @WorkerThread
    suspend fun nukeProducto(){
        return ProductoDAO.nukeTable()
    }
    @WorkerThread
    suspend fun nukeCategoriaProducto(){
        return CategoriaProductoDAO.nukeTable()
    }
    @WorkerThread
    suspend fun nukeMarca(){
        return MarcaDAO.nukeTable()
    }
    @WorkerThread
    suspend fun nukeHistorialVenta(){
        return HistorialVentaDAO.nukeTable()
    }

    @WorkerThread
    suspend fun nukePedidos(){
        return PedidosDAO.nukeTable()
    }
}