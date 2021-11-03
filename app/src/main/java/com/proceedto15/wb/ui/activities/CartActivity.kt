package com.proceedto15.wb.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.proceedto15.wb.R
import com.proceedto15.wb.adapters.CartAdapter
import com.proceedto15.wb.database.entities.Orden
import com.proceedto15.wb.database.entities.OrdenDetalle
import com.proceedto15.wb.database.viewmodels.OrdenViewModel

class CartActivity : AppCompatActivity() {

    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: CartAdapter
    private lateinit var ordenViewModel: OrdenViewModel
    private lateinit var ordenDetalleViewModel: OrdenViewModel
    val rv_cart = findViewById<RecyclerView>(R.id.recycler_cart)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cart_fragment)
        initData()
        initRecycler(emptyList())
    }

    fun initData(){
        ordenViewModel = ViewModelProvider(this).get(OrdenViewModel::class.java)
        ordenDetalleViewModel = ViewModelProvider(this).get(OrdenViewModel::class.java)
    }


    fun initRecycler(list: List<OrdenDetalle>){
        viewManager = LinearLayoutManager(this)
        viewAdapter = CartAdapter(list) { matchItem: OrdenDetalle -> onClicked(matchItem)}
        rv_cart.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }


    }

    fun onClicked(item: OrdenDetalle){

    }
}