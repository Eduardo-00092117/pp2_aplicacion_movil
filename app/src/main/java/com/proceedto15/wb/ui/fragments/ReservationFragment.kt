package com.proceedto15.wb.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.proceedto15.wb.adapters.ServicesAdapter
import com.proceedto15.wb.database.entities.Servicio
import com.proceedto15.wb.database.viewmodels.CitaViewModel
import com.proceedto15.wb.databinding.FragmentReservationBinding

class ReservationFragment : Fragment() {

    private var _binding: FragmentReservationBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: ServicesAdapter
    private lateinit var citaViewModel: CitaViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentReservationBinding.inflate(inflater, container, false)
        val root: View = binding.root
        citaViewModel = ViewModelProvider(this).get(CitaViewModel::class.java)
        binding.totalTimeText.text = "0"

        changeList()
        initRecycler(emptyList())

        return root
    }

    fun initRecycler(list: List<Servicio>) {
        viewManager = LinearLayoutManager(context)
        viewAdapter = ServicesAdapter(list, binding.totalTimeText)
        binding.recyclerServices.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    fun changeList(){
        citaViewModel.allServicio.observe(viewLifecycleOwner, { match ->
            viewAdapter.dataChange(match)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}