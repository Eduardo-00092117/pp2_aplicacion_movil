package com.proceedto15.wb.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.proceedto15.wb.R
import com.proceedto15.wb.database.entities.Servicio

class ServicesAdapter(var servicios: List<Servicio>, val totalTimeText: TextView, val selectedServices: TextView): RecyclerView.Adapter<ServicesAdapter.ServiceHolder>() {

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
            Glide.with(this).load(item.url).into(itemView.findViewById(R.id.imageView))
            itemView.findViewById<CheckBox>(R.id.service_item_check).text = item.nombre
            val view = itemView.findViewById<CheckBox>(R.id.service_item_check)
            itemView.findViewById<CheckBox>(R.id.service_item_check).setOnClickListener{clickListener(item, view)}
        }

        fun textClickListener(item: Servicio, check: CheckBox){
            check.isChecked = check.isChecked == false
            clickListener(item, check)
        }

        fun clickListener(item: Servicio, check: CheckBox){
            var time = totalTimeText.text.toString().toInt()
            val array = selectedServices.text.toString().split(",").toCollection(ArrayList())
            if(check.isChecked){
                time += item.duracion_aprox
                if(!array.contains(item.id.toString())) {
                    array.add(item.id.toString())
                }
            }
            else{
                time -= item.duracion_aprox
                if(array.contains(item.id.toString())) {
                    array.remove(item.id.toString())
                }
            }
            selectedServices.text = array.joinToString(prefix = "", postfix = "", separator = ",")
            //check.text = "Hola"
            totalTimeText.text = time.toString()
        }
    }
}