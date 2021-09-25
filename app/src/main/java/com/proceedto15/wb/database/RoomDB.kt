package com.proceedto15.wb.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.proceedto15.wb.database.daos.*
import com.proceedto15.wb.database.entities.*

@Database(
    entities = arrayOf(
        CategoriaProducto::class,
        CategoriaServicio::class,
        Cita::class,
        CitaXServicio::class,
        Cliente::class,
        Diplomado::class,
        Empleado::class,
        EmpleadoXServicio::class,
        HistorialVenta::class,
        Impartidor::class,
        Marca::class,
        Orden::class,
        OrdenDetalle::class,
        Pago::class,
        Producto::class,
        Servicio::class,
        Usuario::class),
    version = 1)
public abstract class RoomDB : RoomDatabase() {

    abstract fun categoriaProductoDAO(): CategoriaProductoDAO
    abstract fun categoriaServicioDAO(): CategoriaServicioDAO
    abstract fun citaDAO(): CitaDAO
    abstract fun citaXServicioDAO(): CitaXServicioDAO
    abstract fun clienteDAO(): ClienteDAO
    abstract fun diplomadoDAO(): DiplomadoDAO
    abstract fun empleadoDAO(): EmpleadoDAO
    abstract fun empleadoXServicioDAO(): EmpleadoXServicioDAO
    abstract fun historialVentaDAO(): HistorialVentaDAO
    abstract fun impartidorDAO(): ImpartidorDAO
    abstract fun marcaDAO(): MarcaDAO
    abstract fun ordenDAO(): OrdenDAO
    abstract fun ordenDetalleDAO(): OrdenDetalleDAO
    abstract fun pagoDAO(): PagoDAO
    abstract fun productoDAO(): ProductoDAO
    abstract fun servicioDAO(): ServicioDAO
    abstract fun usuarioDAO(): UsuarioDAO

    companion object{

        @Volatile
        private var INSTANCE : RoomDB? = null

        fun getDatabase(context: Context) : RoomDB{
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDB::class.java,
                    "wb_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}