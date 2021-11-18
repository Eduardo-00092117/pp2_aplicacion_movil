package com.proceedto15.wb.database.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.proceedto15.wb.database.RoomDB
import com.proceedto15.wb.database.entities.*
import com.proceedto15.wb.database.repositories.OrdenRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrdenViewModel(private val app: Application) : AndroidViewModel(app){

    private val repository: OrdenRepository
    val allPago: LiveData<List<Pago>>
    val allOrden: LiveData<List<Orden>>
    val allOrdenDetalle: LiveData<List<OrdenDetalle>>
    val allProducto: LiveData<List<Producto>>
    val allCategoriaProducto: LiveData<List<CategoriaProducto>>
    val allMarca: LiveData<List<Marca>>
    val allHistorialVenta: LiveData<List<HistorialVenta>>
    val allPedidos: LiveData<List<Pedidos>>

    init {
        val PagoDAO = RoomDB.getDatabase(app).pagoDAO()
        val OrdenDAO = RoomDB.getDatabase(app).ordenDAO()
        val OrdenDetalleDAO = RoomDB.getDatabase(app).ordenDetalleDAO()
        val ProductoDAO = RoomDB.getDatabase(app).productoDAO()
        val CategoriaProductoDAO = RoomDB.getDatabase(app).categoriaProductoDAO()
        val MarcaDAO = RoomDB.getDatabase(app).marcaDAO()
        val HistorialVentaDAO = RoomDB.getDatabase(app).historialVentaDAO()
        val PedidosDAO = RoomDB.getDatabase(app).pedidosDAO()

        repository = OrdenRepository(
            PagoDAO,
            OrdenDAO,
            OrdenDetalleDAO,
            ProductoDAO,
            CategoriaProductoDAO,
            PedidosDAO,
            MarcaDAO,
            HistorialVentaDAO
        )

        allPago = repository.allPago
        allOrden = repository.allOrden
        allOrdenDetalle = repository.allOrdenDetalle
        allProducto = repository.allProducto
        allCategoriaProducto = repository.allCategoriaProducto
        allPedidos = repository.allPedidos
        allMarca = repository.allMarca
        allHistorialVenta = repository.allHistorialVenta
    }

    // INSERTS

    fun insertPago(pago: Pago) = viewModelScope.launch(Dispatchers.IO){
        repository.insertPago(pago)
    }
    fun insertOrden(orden: Orden) = viewModelScope.launch(Dispatchers.IO){
        repository.insertOrden(orden)
    }
    fun insertOrdenDetalle(ordenDetalle: OrdenDetalle) = viewModelScope.launch(Dispatchers.IO){
        repository.insertOrdenDetalle(ordenDetalle)
    }
    fun insertProducto(producto: Producto) = viewModelScope.launch(Dispatchers.IO){
        repository.insertProducto(producto)
    }
    fun insertCategoriaProducto(categoriaProducto: CategoriaProducto) = viewModelScope.launch(Dispatchers.IO){
        repository.insertCategoriaProducto(categoriaProducto)
    }
    fun insertMarca(marca: Marca) = viewModelScope.launch(Dispatchers.IO){
        repository.insertMarca(marca)
    }
    fun insertHistorialVenta(historialVenta: HistorialVenta) = viewModelScope.launch(Dispatchers.IO){
        repository.insertHistorialVenta(historialVenta)
    }
    fun insertPedido(pedidos: Pedidos) = viewModelScope.launch(Dispatchers.IO){
        repository.insertPedidos(pedidos)
    }

    // DELETES

    fun deleteOnePedido(id: Int) = viewModelScope.launch(Dispatchers.Main){
        repository.deleteOnePedido(id)
    }

    fun subtractExistence(id: Int, amount: Int) = viewModelScope.launch(Dispatchers.IO){
        repository.subtractExistence(id, amount)
    }

    fun updatePedido(id: Int, amount: Int) = viewModelScope.launch(Dispatchers.IO){
        repository.updatePedido(id, amount)
    }

    fun deleteAllPedido() = viewModelScope.launch(Dispatchers.IO){
        repository.deleteAllPedido()
    }

    // GETs

    fun getPago(id: Int) = repository.getPago(id)

    fun getOrden(id: Int) = repository.getOrden(id)

    fun getOrdenDetalle(id: Int) = repository.getOrdenDetalle(id)

    fun getProducto(id: Int) = repository.getProducto(id)

    fun getCategoriaProducto(id: Int) = repository.getCategoriaProducto(id)

    fun getMarca(id: Int) = repository.getMarca(id)

    fun getHistorialVenta(id: Int) = repository.getHistorialVenta(id)

    fun getPedido(id: Int) = repository.getPedido(id)

    // NukeTables

    private suspend fun nukePago() = repository.nukePago()

    private suspend fun nukeOrden() = repository.nukeOrden()

    private suspend fun nukeOrdenDetalle() = repository.nukeOrdenDetalle()

    private suspend fun nukeProducto() = repository.nukeProducto()

    private suspend fun nukeCategoriaProducto() = repository.nukeCategoriaProducto()

    private suspend fun nukeMarca() = repository.nukeMarca()

    private suspend fun nukeHistorialVenta() = repository.nukeHistorialVenta()

    private suspend fun nukePedido() = repository.nukePedidos()
}