package com.proceedto15.wb.adapters;

import com.proceedto15.wb.database.entities.Cita;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.proceedto15.wb.R


class AdminAdapter(var citas: List<Cita>, val clickListener: (Cita) -> Unit): RecyclerView.Adapter<AdminAdapter.AdminHolder>() {
    private lateinit var view: View

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminAdapter.AdminHolder {
        view = LayoutInflater.from(parent.context).inflate(R.layout.admin_item, parent, false)
        return AdminHolder(view)
    }

    override fun onBindViewHolder(holder: AdminAdapter.AdminHolder, position: Int) {
        holder.bind(citas[position], clickListener)
    }

    override fun getItemCount(): Int = citas.size

    inner class AdminHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(item: Cita, clickListener: (Cita) -> Unit) = with(itemView){
            this.setOnClickListener{clickListener(item)}
        }
    }
}
