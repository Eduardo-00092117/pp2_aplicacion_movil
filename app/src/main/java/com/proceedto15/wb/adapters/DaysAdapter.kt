package com.proceedto15.wb.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.proceedto15.wb.R

class DaysAdapter(var days: List<String>, var weekDays: List<String>, val clickListener: (String) -> Unit): RecyclerView.Adapter<DaysAdapter.DayHolder>() {

    private lateinit var view: View

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayHolder {
        view = LayoutInflater.from(parent.context).inflate(R.layout.day_item, parent, false)
        return DayHolder(view)
    }

    override fun onBindViewHolder(holder: DayHolder, pos: Int) {
        holder.bind(days[pos], weekDays[pos], clickListener)
    }

    override fun getItemCount() = days.size

    fun dataChange(listaMatches : List<String>){
        days = listaMatches
        notifyDataSetChanged()
    }

    inner class DayHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(day: String, weekDay: String, clcikListener: (String) -> Unit) = with(itemView) {
            itemView.findViewById<TextView>(R.id.day_item_number).text = day
            itemView.findViewById<TextView>(R.id.day_item_text).text = weekDay
            this.setOnClickListener{clickListener(day)}
        }
    }
}