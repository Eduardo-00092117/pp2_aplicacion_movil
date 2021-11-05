package com.proceedto15.wb.ui.activities

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.proceedto15.wb.R
import com.proceedto15.wb.database.entities.Cita
import com.proceedto15.wb.database.viewmodels.CitaViewModel
import com.proceedto15.wb.databinding.AdminFragmentAddBinding
import com.proceedto15.wb.databinding.AdminFragmentEditBinding
import com.proceedto15.wb.ui.fragments.DatePickerFragment
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AdminEditActivity : AppCompatActivity() {

    private lateinit var editData: Button
    private lateinit var calendar: EditText
    private lateinit var time: EditText
    private lateinit var clientes: Spinner
    private lateinit var citaViewModel: CitaViewModel
    private lateinit var listaclientes: ArrayList<String>
    private lateinit var _binding: AdminFragmentEditBinding
    private lateinit var cita : Cita



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = AdminFragmentEditBinding.inflate(layoutInflater)
        val view = _binding.root
        setContentView(view)

        initData()
    }

    @SuppressLint("SimpleDateFormat")
    fun initData(){
        cita = intent.extras!!.getParcelable("appointment")!!
        editData = findViewById(R.id.edit_appointment)
        calendar = findViewById(R.id.edit_date)
        time = findViewById(R.id.edit_time)
        citaViewModel = ViewModelProvider(this).get(CitaViewModel::class.java)
        clientes = findViewById(R.id.customers_spinner)
        listaclientes = ArrayList()
        bindData()
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

        time.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                time.setText(SimpleDateFormat("HH:mm").format(cal.time))
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }
        editData.setOnClickListener(editDataClickListener)
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

    fun bindData(){
        time.setText(cita.hora)
        calendar.setText(cita.fecha)
    }

    val editDataClickListener = View.OnClickListener {
        if(calendar.text.isEmpty() && time.text.isEmpty()){
            Toast.makeText(this, "Ingrese datos porfavor", Toast.LENGTH_SHORT).show()
        }
        else {
            citaViewModel.insertCita(
                Cita(
                    cita.id,
                    clientes.selectedItem as Int,
                    calendar.text.toString(),
                    time.text.toString()
                )
            )
        }
    }



}
