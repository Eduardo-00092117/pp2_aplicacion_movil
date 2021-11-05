package com.proceedto15.wb.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.proceedto15.wb.adapters.ProductsAdapter
import com.proceedto15.wb.database.entities.Producto
import com.proceedto15.wb.database.viewmodels.OrdenViewModel
import com.proceedto15.wb.databinding.FragmentProductsBinding

class ProductsFragment : Fragment() {

    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: ProductsAdapter
    private lateinit var ordenViewModel: OrdenViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        ordenViewModel = ViewModelProvider(this).get(OrdenViewModel::class.java)

        changeList()
        initRecycler(emptyList())

        return root
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
        
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}