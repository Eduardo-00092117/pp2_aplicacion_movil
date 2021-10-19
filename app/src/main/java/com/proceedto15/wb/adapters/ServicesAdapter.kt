package com.proceedto15.wb.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.proceedto15.wb.R
import com.proceedto15.wb.database.entities.Producto
import com.proceedto15.wb.database.entities.Servicio

class ServicesAdapter(var servicios: List<Servicio>, val totalTimeText: TextView): RecyclerView.Adapter<ServicesAdapter.ServiceHolder>() {

    private lateinit var view: View

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceHolder {
        view = LayoutInflater.from(parent.context).inflate(R.layout.service_item, parent, false)
        return ServiceHolder(view)
    }

    override fun onBindViewHolder(holder: ServiceHolder, pos: Int) {
        holder.bind(servicios[pos])
    }

    override fun getItemCount(): Int = servicios.size

    fun dataChange(listaMatches : List<Servicio>){
        servicios = listaMatches
        notifyDataSetChanged()
    }

    inner class ServiceHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(item: Servicio) = with(itemView) {
            itemView.findViewById<TextView>(R.id.service_item_name).text = item.nombre
            val view = itemView.findViewById<CheckBox>(R.id.service_item_check)
            itemView.findViewById<CheckBox>(R.id.service_item_check).setOnClickListener{clcikListener(item, view)}
        }

        fun clcikListener(item: Servicio, check: CheckBox){
            var time = totalTimeText.text.toString().toInt()
            if(check.isChecked) time += item.duracion_aprox
            else time -= item.duracion_aprox
            totalTimeText.text = time.toString()
        }
    }
}