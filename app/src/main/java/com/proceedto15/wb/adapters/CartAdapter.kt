package com.proceedto15.wb.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.proceedto15.wb.R
import com.proceedto15.wb.database.entities.OrdenDetalle

class CartAdapter(var orden: List<OrdenDetalle>, val clickListener: (OrdenDetalle)-> Unit): RecyclerView.Adapter<CartAdapter.CartHolder>() {

    private lateinit var view: View

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartHolder{
        view = LayoutInflater.from(parent.context).inflate(R.layout.order_item, parent, false)
        return CartHolder(view)
    }

    override fun onBindViewHolder(holder: CartHolder, pos: Int) {
        holder.bind(orden[pos], clickListener)
    }

    override fun getItemCount(): Int = orden.size

    fun dataChange(listorder: List<OrdenDetalle>){
        orden = listorder
        notifyDataSetChanged()
    }

    inner class CartHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(item: OrdenDetalle, clickListener: (OrdenDetalle) -> Unit) = with(itemView){
            itemView.findViewById<TextView>(R.id.cantidad_producto).text = item.cantidad.toString()
            itemView.findViewById<TextView>(R.id.total_orden).text = item.total.toString()
            this.setOnClickListener{clickListener(item)}
        }
    }
}