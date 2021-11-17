package com.proceedto15.wb.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.proceedto15.wb.R
import com.proceedto15.wb.database.entities.Diplomado
import com.proceedto15.wb.database.entities.Servicio

class DiplomadosAdapter(var diplomados: List<Diplomado>, val clicklistener: (Diplomado) -> Unit): RecyclerView.Adapter<DiplomadosAdapter.DiplomadoHolder>() {

    private lateinit var view: View

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiplomadoHolder {
        view = LayoutInflater.from(parent.context).inflate(R.layout.diplomado_item, parent, false)
        return DiplomadoHolder(view)
    }

    override fun onBindViewHolder(holder: DiplomadoHolder, pos: Int) {
        holder.bind(diplomados[pos], clicklistener)
    }

    override fun getItemCount(): Int = diplomados.size

    fun dataChange(listaMatches : List<Diplomado>){
        diplomados = listaMatches
        notifyDataSetChanged()
    }

    inner class DiplomadoHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(item: Diplomado, clickListener: (Diplomado) -> Unit) = with(itemView) {
            Glide.with(this).load(item.url).into(itemView.findViewById(R.id.imageView2))
            itemView.findViewById<TextView>(R.id.diplomado_item_name).text = item.nombre
            itemView.findViewById<TextView>(R.id.diplomado_item_start_date).text = item.fecha_inicio
            itemView.findViewById<TextView>(R.id.diplomado_item_finish_date).text = item.fecha_fin
            this.setOnClickListener{clickListener(item)}
        }
    }
}