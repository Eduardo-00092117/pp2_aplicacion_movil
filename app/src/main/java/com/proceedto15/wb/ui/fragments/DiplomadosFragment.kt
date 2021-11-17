package com.proceedto15.wb.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.proceedto15.wb.adapters.DiplomadosAdapter
import com.proceedto15.wb.database.entities.Diplomado
import com.proceedto15.wb.database.viewmodels.DiplomadoViewModel
import com.proceedto15.wb.databinding.FragmentDiplomadosBinding
import com.proceedto15.wb.ui.activities.DiplomadoInfoActivity
import com.proceedto15.wb.ui.activities.LoginActivity

class DiplomadosFragment : Fragment() {

    private var _binding: FragmentDiplomadosBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: DiplomadosAdapter
    private lateinit var diplomadoViewModel: DiplomadoViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDiplomadosBinding.inflate(inflater, container, false)
        val root: View = binding.root
        diplomadoViewModel = ViewModelProvider(this).get(DiplomadoViewModel::class.java)

        changeList()
        initRecycler(emptyList())

        return root
    }

    fun initRecycler(list: List<Diplomado>) {
        viewManager = LinearLayoutManager(context)
        viewAdapter = DiplomadosAdapter(list) { matchItem: Diplomado -> onClicked(matchItem)}
        binding.recyclerDiplomados.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this.context, 2)
            adapter = viewAdapter
        }
    }

    fun onClicked(item: Diplomado){
        val intent = Intent(context, DiplomadoInfoActivity::class.java)
        intent.putExtra("dipItem", item)
        startActivity(intent)
    }

    fun changeList(){
        diplomadoViewModel.allDiplomado.observe(viewLifecycleOwner, { match ->
            viewAdapter.dataChange(match)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}