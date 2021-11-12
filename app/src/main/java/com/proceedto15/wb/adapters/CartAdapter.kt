package com.proceedto15.wb.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.proceedto15.wb.Models.Ordenes
import com.proceedto15.wb.R
import com.proceedto15.wb.database.entities.OrdenDetalle
import com.proceedto15.wb.database.viewmodels.OrdenViewModel

class CartAdapter(/*var orden: List<OrdenDetalle>*/var orden: MutableList<Ordenes>, var viewModel: OrdenViewModel, val clickListener: (Ordenes)-> Unit): RecyclerView.Adapter<CartAdapter.CartHolder>() {

    private lateinit var view: View

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartHolder{
        view = LayoutInflater.from(parent.context).inflate(R.layout.order_item, parent, false)
        return CartHolder(view)
    }

    override fun onBindViewHolder(holder: CartHolder, pos: Int) {
        holder.bind(orden[pos], clickListener)
    }

    override fun getItemCount(): Int = orden.size

    /*fun dataChange(listorder: /*List<OrdenDetalle>*/ MutableList<Ordenes>){
        orden = listorder
        notifyDataSetChanged()
    }*/

    inner class CartHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(/*item: OrdenDetalle*/item: Ordenes, clickListener: (Ordenes) -> Unit) = with(itemView){
            val media = "https://i0.wp.com/www.senpai.com.mx/wp-content/uploads/2021/04/Fortnite-presenta-la-skin-Diamond-Hanz-inspirada-en-el-meme-Stonks.png?w=1280&ssl=1"
            val image = itemView.findViewById<ImageView>(R.id.images)
            if (media !==null) {
                Glide.with(this)
                    .load(media)
                    .into(image)
            } else {
                image.setImageResource(R.drawable.ic_launcher_background)
            }
            itemView.findViewById<ImageView>(R.id.images)
            itemView.findViewById<TextView>(R.id.id_producto).text = item.nombreProducto
            itemView.findViewById<TextView>(R.id.cantidad_producto).text = "Qty: "+item.Qty.toString()
            itemView.findViewById<TextView>(R.id.total_orden).text = "$"+item.TotalPrice.toString()
            itemView.findViewById<ImageView>(R.id.cross).setOnClickListener{deleteOrder( item)}
            this.setOnClickListener{clickListener(item)}
        }

        fun deleteOrder(item: Ordenes){
            orden.remove(item)
            notifyDataSetChanged()
            Log.d("fuck", item.nombreProducto)
        }
    }
}