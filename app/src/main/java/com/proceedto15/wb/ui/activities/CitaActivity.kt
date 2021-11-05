package com.proceedto15.wb.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Spinner
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.proceedto15.wb.R
import com.proceedto15.wb.database.viewmodels.CitaViewModel
import com.proceedto15.wb.databinding.ActivityMainBinding

class CitaActivity : AppCompatActivity() {

    lateinit var mAuth: FirebaseAuth
    private lateinit var citaViewModel: CitaViewModel
    private lateinit var employeeSpinner: Spinner
    private lateinit var employees: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cita)

        initData()
    }

    fun initData(){
        citaViewModel = ViewModelProvider(this).get(CitaViewModel::class.java)
        employeeSpinner = findViewById(R.id.employee_spinner)
    }
}