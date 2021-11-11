package com.proceedto15.wb.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = CartActivityBinding.inflate(layoutInflater)
        val view =_binding.root
        setContentView(view)
        initData()
        changelist()
        initRecycler(emptyList())
    }

    fun initData(){
        ordenViewModel = ViewModelProvider(this).get(OrdenViewModel::class.java)
        ordenDetalleViewModel = ViewModelProvider(this).get(OrdenViewModel::class.java)
    }


    fun initRecycler(list: List<OrdenDetalle>){
        viewManager = LinearLayoutManager(this)
        viewAdapter = CartAdapter(list) { matchItem: OrdenDetalle -> onClicked(matchItem)}
        _binding.recyclerCart.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    fun changelist(){
        ordenViewModel.allOrdenDetalle.observe(this, {match ->
            viewAdapter.dataChange(match)
        })
    }

    fun onClicked(item: OrdenDetalle){

    }
}