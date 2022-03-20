package com.itpl.iot

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ReadDataAdapter(private val listener: ReadDataActivity): RecyclerView.Adapter<DataViewHolder>() {
    private val items: ArrayList<ReadD> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_readdata,parent,false)
        val viewHolder = DataViewHolder(view)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val currentItem = items[position]
        holder.time.text = currentItem.created_at
        holder.field1.text = currentItem.field1
        holder.field2.text = currentItem.field2

    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateData(updatedData: ArrayList<ReadD>){
        items.clear()
        items.addAll(updatedData)
        notifyDataSetChanged()
    }

}

class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val time: TextView = itemView.findViewById(R.id.time)
    val field1: TextView = itemView.findViewById(R.id.field1)
    val field2: TextView = itemView.findViewById(R.id.field2)
}