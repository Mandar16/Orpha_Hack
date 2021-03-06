package com.example.orpha.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.orpha.R
import com.example.orpha.models.Orphanage
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.DocumentSnapshot




class OrphanageAdapter(options: FirestoreRecyclerOptions<Orphanage>,val listner : IPostAdapter) : FirestoreRecyclerAdapter<Orphanage, OrphanageAdapter.OrphanageAdapterViewHolder>(
    options
) {




    class OrphanageAdapterViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val orphanageName: TextView = itemView.findViewById<TextView>(R.id.orphanageName)
        val numberOfChildren: TextView = itemView.findViewById<TextView>(R.id.numberOfChildren)
        val fundingRequired: TextView = itemView.findViewById<TextView>(R.id.fundingRequired)


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrphanageAdapterViewHolder {

        val viewHolder= OrphanageAdapterViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.orphanage_list_item, parent, false)
        )
        viewHolder.itemView.setOnClickListener{
            listner.onLikeClicked(snapshots.getSnapshot(viewHolder.adapterPosition).id)
            //Toast.makeText(this, snapshots.getSnapshot(viewHolder.adapterPosition).id ,Toast.LENGTH_SHORT)
        }
        return  viewHolder


    }

    override fun onBindViewHolder(
        holder: OrphanageAdapterViewHolder,
        position: Int,
        model: Orphanage
    ) {
        holder.orphanageName.text = model.name
        holder.numberOfChildren.text = "Total Number of Children: " + model.no_of_children.toString()
        holder.fundingRequired.text = "Funding Required : "+model.funding_deficit.toString()
    }



}

interface IPostAdapter{
    fun onLikeClicked(id:String)

}