package com.proceedto15.wb.adapters;

import android.content.Context
import android.content.Intent
import com.proceedto15.wb.database.entities.Cita;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.proceedto15.wb.R
import com.proceedto15.wb.database.entities.Producto
import com.proceedto15.wb.database.viewmodels.CitaViewModel
import com.proceedto15.wb.ui.activities.AdminEditActivity


class AdminAdapter(var citas: List<Cita>, val viewModel: CitaViewModel ,val clickListener: (Cita) -> Unit): RecyclerView.Adapter<AdminAdapter.AdminHolder>() {
    private lateinit var view: View

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminAdapter.AdminHolder {
        view = LayoutInflater.from(parent.context).inflate(R.layout.admin_item, parent, false)
        return AdminHolder(view)
    }

    override fun onBindViewHolder(holder: AdminAdapter.AdminHolder, position: Int) {
        holder.bind(citas[position], clickListener)
    }

    override fun getItemCount(): Int = citas.size

    fun dataChange(listaMatches : List<Cita>){
        citas = listaMatches
        notifyDataSetChanged()
    }

    inner class AdminHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(item: Cita, clickListener: (Cita) -> Unit) = with(itemView){
            itemView.findViewById<TextView>(R.id.admin_item_id).text = item.id.toString()
            itemView.findViewById<TextView>(R.id.admin_item_id_client).text = item.idCliente.toString()
            val v_edit = itemView.findViewById<Button>(R.id.edit)
            itemView.findViewById<Button>(R.id.edit).setOnClickListener{clickListener(item)}
            val v_delete = itemView.findViewById<Button>(R.id.delete)
            itemView.findViewById<Button>(R.id.delete).setOnClickListener{deleteAppointment(item)}
            //this.setOnClickListener{clickListener(item)}
        }


        fun deleteAppointment(item: Cita){
            viewModel.deleteOneCita(item.id)
        }

    }
}