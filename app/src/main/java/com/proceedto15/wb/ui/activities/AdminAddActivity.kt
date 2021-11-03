package com.proceedto15.wb.ui.activities

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.common.api.Api
import com.proceedto15.wb.R
import com.proceedto15.wb.database.entities.Cita
import com.proceedto15.wb.database.entities.Cliente
import com.proceedto15.wb.database.viewmodels.CitaViewModel
import com.proceedto15.wb.ui.fragments.DatePickerFragment
import java.text.SimpleDateFormat

class AdminAddActivity : AppCompatActivity() {

    private lateinit var addData: Button
    private lateinit var calendar: EditText
    private lateinit var time: EditText
    private lateinit var clientes: Spinner
    private lateinit var citaViewModel: CitaViewModel
    private lateinit var listaclientes: ArrayList<String>


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_fragment_add)

        initData()
    }

    fun initData(){
        addData = findViewById(R.id.add_appointment)
        calendar = findViewById(R.id.calendar)
        time = findViewById(R.id.time)
        citaViewModel = ViewModelProvider(this).get(CitaViewModel::class.java)
        clientes = findViewById(R.id.clientes_spinner)
        citaViewModel.allCliente.observe(this, {

            it.forEach{
                val nombrec = it.nombre + " " + it.apellido
                listaclientes.add(nombrec)
            }
            val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, ArrayList<String>())
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            clientes.setAdapter(spinnerAdapter)
            spinnerAdapter.addAll(listaclientes)
        })


        calendar.setOnClickListener(showDatePickerDialog)


    }

    val showDatePickerDialog = View.OnClickListener {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    private fun onDateSelected(day: Int, month: Int, year: Int) {
        val month_date = SimpleDateFormat("MMM")
        val month_name: String = month_date.format(month)

        val date = "$day/$month/$year"
        calendar.setText("$day de $month_name $year")
    }

    val addDataClickListener = View.OnClickListener {
        /*citaViewModel.insertCita(Cita(0, ))
         */
    }

}