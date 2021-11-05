package com.proceedto15.wb.ui.activities

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.common.api.Api
import com.proceedto15.wb.R
import com.proceedto15.wb.database.entities.Cita
import com.proceedto15.wb.database.entities.Cliente
import com.proceedto15.wb.database.viewmodels.CitaViewModel
import com.proceedto15.wb.databinding.AdminFragmentAddBinding
import com.proceedto15.wb.ui.fragments.DatePickerFragment
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AdminAddActivity : AppCompatActivity() {

    private lateinit var addData: Button
    private lateinit var calendar: EditText
    private lateinit var time: EditText
    private lateinit var clientes: Spinner
    private lateinit var citaViewModel: CitaViewModel
    private lateinit var listaclientes: ArrayList<String>
    private lateinit var _binding: AdminFragmentAddBinding
    private var size : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = AdminFragmentAddBinding.inflate(layoutInflater)
        val view = _binding.root
        setContentView(view)

        initData()
    }

    @SuppressLint("SimpleDateFormat")
    fun initData(){
        addData = findViewById(R.id.add_appointment)
        calendar = findViewById(R.id.calendar)
        time = findViewById(R.id.time)
        citaViewModel = ViewModelProvider(this).get(CitaViewModel::class.java)
        clientes = findViewById(R.id.clientes_spinner)
        listaclientes = ArrayList()
        listaclientes.add("Seleccione un cliente")
        citaViewModel.allCliente.observe(this, {

            it.forEach{
                val nombrec =  it.id.toString() + " " + it.nombre + " " + it.apellido
                listaclientes.add(nombrec)
            }
            val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, ArrayList<String>())
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            clientes.setAdapter(spinnerAdapter)
            spinnerAdapter.addAll(listaclientes)
        })

        citaViewModel.allCita.observe(this, {
            size = it.size
        })


        calendar.setOnClickListener(showDatePickerDialog)

        time.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                time.setText(SimpleDateFormat("HH:mm").format(cal.time))
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }

        addData.setOnClickListener(addDataClickListener)
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
        val list = clientes.selectedItem.toString().split(" ")
        if(calendar.text.isEmpty() && time.text.isEmpty()){
            Toast.makeText(this, "Ingrese datos porfavor", Toast.LENGTH_SHORT).show()
        }
        else if(clientes.selectedItem.toString() == "Seleccione un cliente"){
            Toast.makeText(this, "Ingrese un cliente valido", Toast.LENGTH_SHORT).show()
        }
        else {
            citaViewModel.insertCita(
                Cita(
                    size+1,
                    list[0].toInt(),
                    calendar.text.toString(),
                    time.text.toString()
                )
            )
        }

    }

}