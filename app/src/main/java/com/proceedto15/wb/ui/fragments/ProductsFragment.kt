package com.proceedto15.wb.ui.fragments

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.NumberPicker
import android.widget.PopupWindow
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.proceedto15.wb.R
import com.proceedto15.wb.adapters.ProductsAdapter
import com.proceedto15.wb.database.entities.Orden
import com.proceedto15.wb.database.entities.OrdenDetalle
import com.proceedto15.wb.database.entities.Producto
import com.proceedto15.wb.database.viewmodels.OrdenViewModel
import com.proceedto15.wb.databinding.FragmentProductsBinding
import com.proceedto15.wb.ui.activities.CartActivity
import kotlinx.coroutines.NonDisposableHandle.parent
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

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
            ordenViewModel.insertOrden(Orden(0,1,SimpleDateFormat("dd/MM/yyyy").format(Date()),SimpleDateFormat("hh:mm:ss").format(Calendar.getInstance().time) ,false))
            ordenViewModel.insertOrdenDetalle(OrdenDetalle(0, item.id, 0, 1, item.precio, item.precio))
            val intent = Intent(context, CartActivity::class.java)
            startActivity(intent)
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