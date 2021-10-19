package com.proceedto15.wb.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.proceedto15.wb.database.entities.OrdenDetalle

class CartAdapter(var orden: List<OrdenDetalle>, val clickListener: (OrdenDetalle)-> Unit): RecyclerView.Adapter<CartAdapter.AdminHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.AdminHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: CartAdapter.AdminHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = orden.size

    inner class AdminHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(item: OrdenDetalle, clickListener: (OrdenDetalle) -> Unit) = with(itemView){
            this.setOnClickListener{clickListener(item)}
        }
    }
}