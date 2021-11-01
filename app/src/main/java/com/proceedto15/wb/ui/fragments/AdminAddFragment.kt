package com.proceedto15.wb.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.proceedto15.wb.R
import com.proceedto15.wb.adapters.AdminAdapter
import com.proceedto15.wb.database.entities.Cita

class AdminAddFragment : Fragment() {

    //private var _binding: FragmentAdminBinding? = null
    //private val binding get() = _binding!!

    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: AdminAdapter

    //private lateinit var viewModel: AdminViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.admin_activity_crud, container, false)
    }

    fun initRecycler(list: List<Cita>){
        viewManager = LinearLayoutManager(context)
        viewAdapter = AdminAdapter(list, { listItem: Cita -> onClicked(listItem)})
        /*binding.recyclerAdmin.apply{
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter

        }*/
    }

    fun onClicked(item: Cita){

    }

    override fun onDestroyView() {
        super.onDestroyView()
        //_binding = null
    }

}