package com.proceedto15.wb.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.proceedto15.wb.R
import com.proceedto15.wb.database.entities.Diplomado

class DiplomadoInfoActivity : AppCompatActivity() {
    lateinit var dip : Diplomado
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diplomado_info)
         bindItems()
    }

    fun bindItems() {
        dip = intent.extras!!.getParcelable("dipItem")!!
        val txtNombre : TextView = findViewById(R.id.diplomado_info_nombre)
        val txtLugar : TextView = findViewById(R.id.ddiplomado_info_lugar)
        val txtCupo : TextView = findViewById(R.id.diplomado_info_cupo)
        val txtFechaIni : TextView = findViewById(R.id.ddiplomado_info_fechaIni)
        val txtFechaFin : TextView = findViewById(R.id.ddiplomado_info_FechaFin)
        val txtDuracion : TextView = findViewById(R.id.ddiplomado_info_duracion)
        val txtPrecio : TextView = findViewById(R.id.ddiplomado_info_precio)
        val txtDesc : TextView = findViewById(R.id.ddiplomado_info_desc)

        txtNombre.text = dip.nombre
        txtLugar.text = dip.lugar
        txtCupo.text = dip.cupo.toString()
        txtFechaIni.text = dip.fecha_inicio
        txtFechaFin.text = dip.fecha_fin
        txtDuracion.text = dip.duracion
        txtPrecio.text = dip.precio.toString()
        txtDesc.text = dip.descripcion
    }
}