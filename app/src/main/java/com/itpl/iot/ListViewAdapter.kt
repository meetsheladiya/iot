package com.itpl.iot

import android.content.Context
import com.itpl.iot.Hero
import android.widget.ArrayAdapter
import com.itpl.iot.R
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView

class ListViewAdapter(private val heroList: List<Hero>, private val mCtx: Context) :
    ArrayAdapter<Hero?>(
        mCtx, R.layout.list_items, heroList
    ) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(mCtx)
        val listViewItem = inflater.inflate(R.layout.list_items, null, true)
        val textViewTime = listViewItem.findViewById<TextView>(R.id.textViewTime)
        val textViewField1 = listViewItem.findViewById<TextView>(R.id.textViewField1)
        val textViewField2 = listViewItem.findViewById<TextView>(R.id.textViewField2)
        val hero = heroList[position]
        textViewTime.text = hero.name
        textViewField1.text = hero.field1
        textViewField2.text = hero.field2
        return listViewItem
    }
}