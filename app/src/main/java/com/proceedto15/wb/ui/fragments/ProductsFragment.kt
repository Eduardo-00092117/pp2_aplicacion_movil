package com.proceedto15.wb.ui.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import com.proceedto15.wb.R
import android.widget.NumberPicker
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.proceedto15.wb.adapters.ProductsAdapter
import com.proceedto15.wb.database.entities.Pedidos
import com.proceedto15.wb.database.entities.Producto
import com.proceedto15.wb.database.viewmodels.OrdenViewModel
import com.proceedto15.wb.databinding.FragmentProductsBinding

class ProductsFragment : Fragment() {

    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: ProductsAdapter
    private lateinit var ordenViewModel: OrdenViewModel
    private lateinit var builder: AlertDialog.Builder

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProductsBinding.inflate(inflater, container, false)

        initData()
        changeList()
        initRecycler(emptyList())

        return binding.root
    }

    fun initData(){
        ordenViewModel = ViewModelProvider(this).get(OrdenViewModel::class.java)
        builder = AlertDialog.Builder(requireContext())
    }

    fun initRecycler(list: List<Producto>) {
        viewManager = LinearLayoutManager(context)
        viewAdapter = ProductsAdapter(list, { listItem: Producto -> onClicked(listItem)})
        binding.recyclerProducts.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this.context, 2)
            adapter = viewAdapter
        }
    }

    fun changeList(){
        ordenViewModel.allProducto.observe(viewLifecycleOwner, { match ->
            viewAdapter.dataChange(match)
        })
    }

    fun onClicked(item: Producto) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_product, null)

        builder.setMessage("Desea agregar el producto "+ '"' + item.nombre + '"' + " al carrito de compras?")
        builder.setView(dialogView)
        val nPicker = dialogView.findViewById<NumberPicker>(R.id.product_picker)
        nPicker.minValue = 1
        nPicker.maxValue = item.existencia
        builder.setPositiveButton(getString(R.string.add), DialogInterface.OnClickListener{ dialog, id ->
            ordenViewModel.insertPedido(Pedidos(0,item.nombre, nPicker.value, item.precio ,nPicker.value*item.precio, item.url))
            Toast.makeText(context, getString(R.string.added_product), Toast.LENGTH_SHORT).show()
        })
        builder.setNegativeButton(getString(R.string.cancel), DialogInterface.OnClickListener { dialog, id ->
            dialog.cancel()
        })

        val alert = builder.create()
        alert?.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}