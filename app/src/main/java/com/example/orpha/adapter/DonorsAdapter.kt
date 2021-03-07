package com.example.orpha.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.orpha.models.Users
import java.util.ArrayList

class DonorsAdapter(val items:ArrayList<Users>):RecyclerView.Adapter<DonorsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DonorsViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1,parent,false)
        return DonorsViewHolder(view)
    }

    override fun onBindViewHolder(holder: DonorsViewHolder, position: Int) {

        val currentItem = items[position]
        holder.donorsText.text = currentItem.displayName
    }

    override fun getItemCount(): Int {

        return items.size
    }
}

class DonorsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    val donorsText:TextView = itemView.findViewById(android.R.id.text1)
}