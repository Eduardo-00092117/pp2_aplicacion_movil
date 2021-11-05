package com.proceedto15.wb.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.PopupMenu
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
import com.proceedto15.wb.utilities.PopulateDB
import com.proceedto15.wb.utilities.Preferences

class AdminActivity: AppCompatActivity() {

    lateinit var mAuth: FirebaseAuth
    private lateinit var firstTime: Preferences
    private lateinit var _binding: AdminActivityCrudBinding

    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: AdminAdapter
    private lateinit var citaViewModel: CitaViewModel
    private lateinit var plusButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = AdminActivityCrudBinding.inflate(layoutInflater)
        citaViewModel = ViewModelProvider(this).get(CitaViewModel::class.java)
        val view = _binding.root
        setContentView(view)

        initData()
        ifFirstTime()
        changeList()
        initRecycler(emptyList())
    }

    fun initData(){
        mAuth = FirebaseAuth.getInstance()
        firstTime = Preferences(applicationContext)

        plusButton = findViewById(R.id.add_appointment)
        plusButton.setOnClickListener(plusClickListener)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.user_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.logout_action -> {
                mAuth.signOut()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    fun ifFirstTime(){
        if(firstTime.firstTime == ""){
            firstTime.firstTime = "1"
            PopulateDB(this).populate()
        }
    }

    fun changeList(){
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
    }

    fun onClicked(item: Cita) {
        val extras = Bundle()
        extras.putParcelable("appointment", item)
        val intent = Intent(this, AdminEditActivity::class.java).putExtras(extras)
        startActivity(intent)
    }
}


