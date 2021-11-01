package com.proceedto15.wb.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.proceedto15.wb.R
import com.proceedto15.wb.adapters.AdminAdapter
import com.proceedto15.wb.database.entities.Cita
import com.proceedto15.wb.database.viewmodels.CitaViewModel

class AdminActivity: AppCompatActivity() {

    lateinit var mAuth: FirebaseAuth

    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: AdminAdapter
    private lateinit var citaViewModel: CitaViewModel
    private lateinit var plusButton: FloatingActionButton
    val rv_appointment = findViewById<RecyclerView>(R.id.recycler_appointment)

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_activity_crud)
        initData()
        initRecycler(emptyList())

    }

    fun initData(){
        mAuth = FirebaseAuth.getInstance()
    }



    fun initRecycler(list: List<Cita>){
        viewManager = LinearLayoutManager(this)
        viewAdapter = AdminAdapter(list, citaViewModel ,{listItem: Cita -> onClicked(listItem)})
        rv_appointment.apply {
                setHasFixedSize(true)
                layoutManager = viewManager
                adapter = viewAdapter
            }

        }

    val plusClickListener = View.OnClickListener {
        val intent = Intent(this, AdminAddActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun onClicked(item: Cita) {}

}