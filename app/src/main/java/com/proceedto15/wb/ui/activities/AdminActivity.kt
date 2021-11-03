package com.proceedto15.wb.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.proceedto15.wb.R
import com.proceedto15.wb.adapters.AdminAdapter
import com.proceedto15.wb.database.entities.Cita
import com.proceedto15.wb.database.viewmodels.CitaViewModel
import com.proceedto15.wb.database.viewmodels.OrdenViewModel
import com.proceedto15.wb.databinding.AdminActivityCrudBinding

class AdminActivity: AppCompatActivity() {

    lateinit var mAuth: FirebaseAuth
    private lateinit var _binding: AdminActivityCrudBinding

    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: AdminAdapter
    private lateinit var citaViewModel: CitaViewModel
    private lateinit var plusButton: FloatingActionButton
    private lateinit var rvappointment: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = AdminActivityCrudBinding.inflate(layoutInflater)
        citaViewModel = ViewModelProvider(this).get(CitaViewModel::class.java)
        val view = _binding.root
        setContentView(view)
        //initData()
        list()
        initRecycler(emptyList())

    }

    fun initData(){
        mAuth = FirebaseAuth.getInstance()
    }

    fun list(){
        citaViewModel.allCita.observe(this, { match ->
            viewAdapter.dataChange(match)
        })
    }



    fun initRecycler(list: List<Cita>) {
        viewManager = LinearLayoutManager(this)
        viewAdapter = AdminAdapter(list, citaViewModel, { listItem: Cita -> onClicked(listItem) })
        _binding.recyclerAppointment.apply {
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


