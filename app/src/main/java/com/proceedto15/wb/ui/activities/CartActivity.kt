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
import com.proceedto15.wb.database.entities.Pedidos
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

        setContentView(_binding.root)
        initData()
        changelist()
        fillList()
        initRecycler(emptyList())
    }

    fun initData(){
        ordenViewModel = ViewModelProvider(this).get(OrdenViewModel::class.java)
        ordenDetalleViewModel = ViewModelProvider(this).get(OrdenViewModel::class.java)
    }

    fun initRecycler(list: List<Pedidos>){
        viewManager = LinearLayoutManager(this)
        viewAdapter = CartAdapter(list, ordenDetalleViewModel) { matchItem: Pedidos -> onClicked(matchItem)}
        _binding.recyclerCart.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    fun changelist(){
        ordenViewModel.allPedidos.observe(this, {match ->
            viewAdapter.dataChange(match)
        })
    }

    fun onClicked(item: Pedidos){
        ordenViewModel.deleteOnePedido(item.id)
        changelist()
    }

    fun fillList(){
        ordenViewModel.allPedidos.observe(this,{
            var i = 0F
            it.forEach {
                i += it.TotalPrice
            }
            findViewById<TextView>(R.id.total_cart).text = "$"+ i.toString()

            if (it.isEmpty()){
                Toast.makeText(this, "El carrito se encuentra vacio", Toast.LENGTH_SHORT).show()
            }

            findViewById<TextView>(R.id.payment).setOnClickListener { view ->
                if (it.isEmpty()) {
                    Toast.makeText(this, "El carrito se encuentra vacio", Toast.LENGTH_SHORT).show()
                }
                else{
                    ordenViewModel.deleteAllPedido()
                    Toast.makeText(this, "Pago realizado con exito", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                }
            }
        })
    }
}