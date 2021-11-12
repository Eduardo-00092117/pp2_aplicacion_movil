package com.proceedto15.wb.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.proceedto15.wb.R
import com.proceedto15.wb.database.entities.Pedidos
import com.proceedto15.wb.database.viewmodels.OrdenViewModel

class CartAdapter(var orden: List<Pedidos>, var viewModel: OrdenViewModel, val clickListener: (Pedidos)-> Unit): RecyclerView.Adapter<CartAdapter.CartHolder>() {

    private lateinit var view: View

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartHolder{
        view = LayoutInflater.from(parent.context).inflate(R.layout.order_item, parent, false)
        return CartHolder(view)
    }

    override fun onBindViewHolder(holder: CartHolder, pos: Int) {
        holder.bind(orden[pos], clickListener)
    }

    override fun getItemCount(): Int = orden.size

    fun dataChange(listorder: List<Pedidos>){
        orden = listorder
        notifyDataSetChanged()
    }

    inner class CartHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(item: Pedidos, clickListener: (Pedidos) -> Unit) = with(itemView){
            itemView.findViewById<TextView>(R.id.id_producto).text = item.PName
            itemView.findViewById<TextView>(R.id.cantidad_producto).text = "Cantidad: "+item.Qty.toString()
            itemView.findViewById<TextView>(R.id.total_orden).text = "$"+item.TotalPrice.toString()
            itemView.findViewById<ImageView>(R.id.cross).setOnClickListener{clickListener( item)}
            this.setOnClickListener{clickListener(item)}
        }
    }
}