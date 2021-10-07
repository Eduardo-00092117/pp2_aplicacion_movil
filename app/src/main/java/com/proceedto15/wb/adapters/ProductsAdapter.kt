package com.proceedto15.wb.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.proceedto15.wb.R
import com.proceedto15.wb.database.entities.Producto

class ProductsAdapter(var productos: List<Producto>, val clickListener: (Producto) -> Unit): RecyclerView.Adapter<ProductsAdapter.ProductHolder>() {

    private lateinit var view: View

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        view = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ProductHolder(view)
    }

    override fun onBindViewHolder(holder: ProductHolder, pos: Int) {
        holder.bind(productos[pos], clickListener)
    }

    override fun getItemCount(): Int = productos.size

    fun dataChange(listaMatches : List<Producto>){
        productos = listaMatches
        notifyDataSetChanged()
    }

    inner class ProductHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(item: Producto, clcikListener: (Producto) -> Unit) = with(itemView) {
            itemView.findViewById<TextView>(R.id.product_item_name).text = item.nombre
            itemView.findViewById<TextView>(R.id.product_item_stock).text = item.existencia.toString()
            this.setOnClickListener{clcikListener(item)}
        }
    }
}