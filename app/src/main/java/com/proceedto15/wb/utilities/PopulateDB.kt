package com.proceedto15.wb.utilities

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.proceedto15.wb.database.entities.*
import com.proceedto15.wb.database.viewmodels.CitaViewModel
import com.proceedto15.wb.database.viewmodels.OrdenViewModel

class PopulateDB(context: ViewModelStoreOwner) {

    private val ordenViewModel = ViewModelProvider(context).get(OrdenViewModel::class.java)
    private val citaViewModel = ViewModelProvider(context).get((CitaViewModel::class.java))

    fun populate(){
        populateCategoria()
        populateMarca()
        populateProducto()

        populateCategoriaServicio()
        populateServicio()
        populateEmpleado()
        populateEmpleadoXServicio()
    }

    fun populateCategoria(){
        ordenViewModel.insertCategoriaProducto(CategoriaProducto(1, "Shampú", "Shampú para el cabello"))
        ordenViewModel.insertCategoriaProducto(CategoriaProducto(2, "Acondicionador", "Acondicionador para el cabello"))
        ordenViewModel.insertCategoriaProducto(CategoriaProducto(3, "Crema", "Crema para el cabello"))
    }

    fun populateMarca(){
        ordenViewModel.insertMarca(Marca(1, "Pantene", "Marca de productos para el tratamiento del cabello"))
        ordenViewModel.insertMarca(Marca(2, "Firenze", "Marca de productos para el tratamiento del cabello"))
        ordenViewModel.insertMarca(Marca(3, "Head & Shoulders", "Marca de productos para el tratamiento del cabello"))
        ordenViewModel.insertMarca(Marca(4, "Dove", "Marca de productos para el tratamiento del cabello"))
        ordenViewModel.insertMarca(Marca(5, "Sedal", "Marca de productos para el tratamiento del cabello"))
        ordenViewModel.insertMarca(Marca(6, "NIVEA", "Marca de productos para el tratamiento del cabello"))
    }

    fun populateProducto(){
        ordenViewModel.insertProducto(Producto(0, 1, 1, "Shampú repara y protege", 6F,
            "Limpia y fortalece el pelo con nutrientes Pro-V activos y combate al instante los signos del daño.", 30))
        ordenViewModel.insertProducto(Producto(0, 1, 1, "Shampú adiós frizz", 7.5F,
            "La colección Adiós Frizz de Pantene combina el poder de la ciencia Pro-V con la biotina, el extracto de flor de cactus y el aceite de argán.", 20))
        ordenViewModel.insertProducto(Producto(0, 1, 1, "Shampú sedoso y brillante", 5F,
            "Champú Sin Sulfatos Sedoso & Brillante Pantene Pro-V Miracles, con Biotina + Proteína de seda hidrolizada para pelos excesivamente perjudicados, dañados y con mechas.", 40))
        ordenViewModel.insertProducto(Producto(0, 1, 2, "Firenze shampoo color care", 15F,
            "Contiene extracto de manzanilla que permite relajar el cuero cabelludo luego de la exposición a los químicos del tinturado.", 100))
        ordenViewModel.insertProducto(Producto(0, 2, 2, "Firenze conditiones curl control", 13F,
            "Contiene siliconas que permiten acondicionar el cabello evitando el frizz.", 30))
        ordenViewModel.insertProducto(Producto(0, 1, 5, "Shampú Sedal purificación e hidratación", 12F,
            "Shampoo Sedal Purificación e Hidratación, deja tu cabello lleno de vida, profundamente limpio y nutrido.", 5))
    }

    fun populateCategoriaServicio(){
        citaViewModel.insertCategoriaServicio(CategoriaServicio(1, "Cabello", "Cuido y arreglado del cabello"))
        citaViewModel.insertCategoriaServicio(CategoriaServicio(2, "Maquillaje", "Maquillaje para distintas ocasiones"))
        citaViewModel.insertCategoriaServicio(CategoriaServicio(3, "Manicure", "Cuido y pintado de uñas"))
        citaViewModel.insertCategoriaServicio(CategoriaServicio(4, "Pericure", "Cuido de los pies"))

    }

    fun populateServicio(){
        citaViewModel.insertServicio(Servicio(1, 1, "Corte de cabello", 15, "Corte de cabello"))
        citaViewModel.insertServicio(Servicio(2, 1, "PLanchado de cabello", 30, "Planchado de cabello"))
        citaViewModel.insertServicio(Servicio(3, 1, "Lavado de cabello", 10, "Lavado de cabello"))
        citaViewModel.insertServicio(Servicio(4, 1, "Secado de cabello", 30, "Secado de cabello"))
        citaViewModel.insertServicio(Servicio(5, 1, "Tintado de cabello", 60, "Tintado de cabello con el color de su preferencia"))
        citaViewModel.insertServicio(Servicio(6, 1, "Extensiones para el cabello", 60, "Extensiones para el cabello"))
    }

    fun populateEmpleado(){
        citaViewModel.insertEmpleado(Empleado(1, 0, "Wilfredo", "Barraza", true))
        citaViewModel.insertEmpleado(Empleado(2, 0, "Britani", "Esperanza", true))
        citaViewModel.insertEmpleado(Empleado(3, 0, "Luis", "Salazar", true))
        citaViewModel.insertEmpleado(Empleado(4, 0, "Jonathan", "Mendez", true))
    }

    fun populateEmpleadoXServicio(){
        citaViewModel.insertEmpleadoXServicio(EmpleadoXServicio(1, 1, 1))
        citaViewModel.insertEmpleadoXServicio(EmpleadoXServicio(2, 1, 2))
        citaViewModel.insertEmpleadoXServicio(EmpleadoXServicio(3, 1, 3))
        citaViewModel.insertEmpleadoXServicio(EmpleadoXServicio(4, 1, 4))
        citaViewModel.insertEmpleadoXServicio(EmpleadoXServicio(5, 1, 5))
        citaViewModel.insertEmpleadoXServicio(EmpleadoXServicio(6, 1, 6))

        citaViewModel.insertEmpleadoXServicio(EmpleadoXServicio(7, 2, 2))
        citaViewModel.insertEmpleadoXServicio(EmpleadoXServicio(8, 2, 3))
        citaViewModel.insertEmpleadoXServicio(EmpleadoXServicio(9, 2, 4))
        citaViewModel.insertEmpleadoXServicio(EmpleadoXServicio(10, 2, 5))

        citaViewModel.insertEmpleadoXServicio(EmpleadoXServicio(11, 3, 1))
        citaViewModel.insertEmpleadoXServicio(EmpleadoXServicio(12, 3, 3))
        citaViewModel.insertEmpleadoXServicio(EmpleadoXServicio(13, 3, 4))
        citaViewModel.insertEmpleadoXServicio(EmpleadoXServicio(14, 3, 6))

        citaViewModel.insertEmpleadoXServicio(EmpleadoXServicio(15, 4, 3))
        citaViewModel.insertEmpleadoXServicio(EmpleadoXServicio(16, 4, 4))
        citaViewModel.insertEmpleadoXServicio(EmpleadoXServicio(17, 4, 5))
        citaViewModel.insertEmpleadoXServicio(EmpleadoXServicio(18, 4, 6))
    }
}