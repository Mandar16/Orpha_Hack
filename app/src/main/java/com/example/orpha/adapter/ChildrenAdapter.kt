package com.example.orpha.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.orpha.OrphanageRegisterActivity
import com.example.orpha.R
import com.example.orpha.models.Children

class ChildrenAdapter(val items:ArrayList<Children>):RecyclerView.Adapter<ChildrenViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildrenViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.children_list_item,parent,false)
        return ChildrenViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChildrenViewHolder, position: Int) {

        val name = items[position].name
        val age = items[position].age.toString()
        val childImageUrl = items[position].childImageUrl

        holder.childName.text = name
        holder.childAge.text = age
        Glide.with(holder.childImage.context).load(childImageUrl).circleCrop().into(holder.childImage);

    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class ChildrenViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    val childName: TextView = itemView.findViewById(R.id.childName)
    val childAge: TextView = itemView.findViewById(R.id.childAge)
    val childImage: ImageView = itemView.findViewById(R.id.childImage)
}