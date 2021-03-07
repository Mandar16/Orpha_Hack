package com.example.orpha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.orpha.Daos.OrphanageDaos
import com.example.orpha.adapter.ChildrenAdapter
import com.example.orpha.databinding.ActivityOrphansDisplayBinding
import com.example.orpha.databinding.ChildrenListItemBinding
import com.example.orpha.models.Children
import com.example.orpha.models.Orphanage

class OrphansDisplayActivity : AppCompatActivity() {

    var list_of_children : ArrayList<Children> = ArrayList()

    private lateinit var binding:ActivityOrphansDisplayBinding

    lateinit var childrenAdapter : ChildrenAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrphansDisplayBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //setContentView(R.layout.activity_orphans_display)

        val documentId: String? = intent.getStringExtra("documentId")

        val child : Children = Children("Aviral", 12, "https://firebasestorage.googleapis.com/v0/b/orpha-915b0.appspot.com/o/upload%2FARHljkP0EFQxyY8xjetJ6oZ7iv630?alt=media&token=a3c241fe-1e2b-4594-99b9-d47f659c204e")
        list_of_children.add(child)



        val orphanageDaos = OrphanageDaos()

        if (documentId != null) {
            orphanageDaos.getOrphanage(documentId).addOnSuccessListener {
                val orphanage = it.toObject(Orphanage::class.java)

                if (orphanage != null) {
                    list_of_children = orphanage.list_of_childrens!!
                }

                childrenAdapter = ChildrenAdapter(list_of_children)
                binding.orphansDisplayRv.adapter = childrenAdapter
                binding.orphansDisplayRv.layoutManager = LinearLayoutManager(this)

            }
        }


    }
}