package com.example.orpha.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class List_Of_Co_Founders_Adapter(val items:ArrayList<String>): RecyclerView.Adapter<List_Of_Co_Founders_ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): List_Of_Co_Founders_ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1,parent,false)

        return List_Of_Co_Founders_ViewHolder(view)
    }

    override fun onBindViewHolder(holder: List_Of_Co_Founders_ViewHolder, position: Int) {

        val currentItem = items[position]
        holder.co_founders_Text.text = currentItem
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class List_Of_Co_Founders_ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    val co_founders_Text:TextView = itemView.findViewById(android.R.id.text1)
}