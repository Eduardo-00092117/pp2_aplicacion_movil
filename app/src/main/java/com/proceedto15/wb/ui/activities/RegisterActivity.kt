package com.proceedto15.wb.ui.activities

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.proceedto15.wb.R
import com.proceedto15.wb.database.entities.Cliente
import com.proceedto15.wb.database.entities.Usuario
import com.proceedto15.wb.database.viewmodels.CitaViewModel
import com.proceedto15.wb.ui.fragments.DatePickerFragment
import java.text.SimpleDateFormat

class RegisterActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var registerBtn: Button
    private lateinit var emailBox: EditText
    private lateinit var nameBox: EditText
    private lateinit var lastNameBox: EditText
    private lateinit var birthdayBox: EditText
    private lateinit var pswdBox: EditText
    private lateinit var birthday: String
    private lateinit var citaViewModel: CitaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initData()
    }

    fun initData(){
        mAuth = FirebaseAuth.getInstance()

        registerBtn = findViewById(R.id.cirRegisterButton)
        emailBox = findViewById(R.id.registerTextEmail)
        nameBox = findViewById(R.id.registerTextName)
        lastNameBox = findViewById(R.id.registerTextLastName)
        birthdayBox = findViewById(R.id.registerBirthday)
        pswdBox = findViewById(R.id.registerTextPassword)

        birthdayBox.setOnClickListener(showDatePickerDialog)
        registerBtn.setOnClickListener(registerClickListener)

        citaViewModel = ViewModelProvider(this).get(CitaViewModel::class.java)
    }

    val showDatePickerDialog = View.OnClickListener {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    private fun onDateSelected(day: Int, month: Int, year: Int) {
        val month_date = SimpleDateFormat("MMM")
        val month_name: String = month_date.format(month)

        birthday = "$day/$month/$year"
        birthdayBox.setText("$day de $month_name $year")
    }

    val registerClickListener = View.OnClickListener {
        //Se obtiene el correo electrónico y la contraseña
        val email = emailBox.text.toString()
        val pass = pswdBox.text.toString()

        //Se verifica que ninguno de los campos vengan vacios
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)){
            if(TextUtils.isEmpty(email)) emailBox.error = getString(R.string.not_empty)
            else emailBox.error = null
            if(TextUtils.isEmpty(pass)) pswdBox.error = getString(R.string.not_empty)
            else pswdBox.error = null
            return@OnClickListener
        }

        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo

        //Se verifica que exista conexion a internet
        if(activeNetwork != null && activeNetwork.isConnected){
            mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                if(task.isSuccessful){
                    Toast.makeText(this, getString(R.string.login_successful), Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else{
                    Toast.makeText(this, getString(R.string.mail_pass_incorrect), Toast.LENGTH_SHORT).show()
                }
            }
        }
        else{
            Toast.makeText(this, getString(R.string.not_connected), Toast.LENGTH_SHORT).show()
        }

        createUserClient()
    }

    fun createUserClient(){
        val email = emailBox.text.toString()
        val pass = pswdBox.text.toString()
        val name = nameBox.text.toString()
        val lastName = lastNameBox.text.toString()

        //citaViewModel.insertUsuario(Usuario(0, email, pass))
        /*citaViewModel.getUsuarioPorEmail(email).observe(this,{
            citaViewModel.insertCliente(Cliente(0, it.id, name, lastName, birthday))
        })*/
    }
}