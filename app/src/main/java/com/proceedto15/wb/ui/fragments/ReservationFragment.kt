package com.proceedto15.wb.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.proceedto15.wb.R
import com.proceedto15.wb.adapters.ServicesAdapter
import com.proceedto15.wb.database.entities.Servicio
import com.proceedto15.wb.database.viewmodels.CitaViewModel
import com.proceedto15.wb.databinding.FragmentReservationBinding
import com.proceedto15.wb.ui.activities.CitaActivity
import com.proceedto15.wb.utilities.AppConstants.SELECTED_SERVICES_KEY

class ReservationFragment : Fragment() {

    private var _binding: FragmentReservationBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: ServicesAdapter
    private lateinit var citaViewModel: CitaViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentReservationBinding.inflate(inflater, container, false)
        val root: View = binding.root
        initData()
        changeList()
        initRecycler(emptyList())

        return root
    }

    fun initData(){
        citaViewModel = ViewModelProvider(this).get(CitaViewModel::class.java)

        binding.totalTimeText.text = "0"
        binding.toReservationBtn.setOnClickListener(reservationClickListener)
    }

    fun initRecycler(list: List<Servicio>) {
        viewManager = LinearLayoutManager(context)
        viewAdapter = ServicesAdapter(list, binding.totalTimeText, binding.selectedServices)
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

    val reservationClickListener = View.OnClickListener {
        if(binding.selectedServices.text.toString() != "") {
            val intent = Intent(context, CitaActivity::class.java).putExtra(SELECTED_SERVICES_KEY, binding.selectedServices.text.toString())
            startActivity(intent)
        }
        else {
            Toast.makeText(context, getString(R.string.select_services), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}