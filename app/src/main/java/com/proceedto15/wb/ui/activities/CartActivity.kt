package com.proceedto15.wb.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.proceedto15.wb.Models.Ordenes
import com.proceedto15.wb.R
import com.proceedto15.wb.adapters.CartAdapter
import com.proceedto15.wb.database.entities.OrdenDetalle
import com.proceedto15.wb.database.viewmodels.OrdenViewModel
import com.proceedto15.wb.databinding.CartActivityBinding

class CartActivity : AppCompatActivity() {

    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: CartAdapter
    private lateinit var ordenViewModel: OrdenViewModel
    private lateinit var ordenDetalleViewModel: OrdenViewModel
    private lateinit var _binding: CartActivityBinding
    var ordenes = mutableListOf<Ordenes>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = CartActivityBinding.inflate(layoutInflater)
        val view =_binding.root
        setContentView(view)
        initData()
        //changelist()
        fillList()
        initRecycler(emptyList())
    }

    fun initData(){
        ordenViewModel = ViewModelProvider(this).get(OrdenViewModel::class.java)
        ordenDetalleViewModel = ViewModelProvider(this).get(OrdenViewModel::class.java)
    }


    fun initRecycler(list: List<OrdenDetalle>){
        viewManager = LinearLayoutManager(this)
        //viewAdapter = CartAdapter(list, ordenDetalleViewModel) { matchItem: OrdenDetalle -> onClicked(matchItem)}
        viewAdapter = CartAdapter(ordenes, ordenDetalleViewModel) { matchItem: Ordenes -> onClicked(matchItem)}
        _binding.recyclerCart.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    /*fun changelist(){
        ordenViewModel.allOrdenDetalle.observe(this, {match ->
            viewAdapter.dataChange(match)
        })
    }*/

    fun onClicked(item: Ordenes){
        ordenes.remove(item)
    }

    fun fillList(){
        ordenes.add(Ordenes("Shampoo control caspa.", 2, 14.99F, 2*14.99F))
        ordenes.add(Ordenes("Shampoo con acondicionador.", 3, 19.99F, 3*19.99F))
        var i = 0F
        ordenes.forEach {
            i += it.TotalPrice
        }
        findViewById<TextView>(R.id.total_cart).text = "$"+ i.toString()
        findViewById<TextView>(R.id.payment).setOnClickListener {
            Toast.makeText(this, "Pago realizado con exito", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }


}