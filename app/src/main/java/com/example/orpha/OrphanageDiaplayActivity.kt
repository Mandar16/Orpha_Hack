package com.example.orpha

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.orpha.Daos.OrphanageDaos
import com.example.orpha.adapter.OrphanageAdapter
import com.example.orpha.databinding.ActivityOrphanageDiaplayBinding
import com.example.orpha.models.Orphanage
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class OrphanageDiaplayActivity : AppCompatActivity() {
    lateinit var  Orphadapter:OrphanageAdapter
    private lateinit var binding: ActivityOrphanageDiaplayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrphanageDiaplayBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupRecyclerView()

    }

    private fun setupRecyclerView() {
        val orphanageDaos = OrphanageDaos()
        val orphanageCollection = orphanageDaos.orphanageCollections
        val query = orphanageCollection
            .limit(50);

        val recyclerViewOptions = FirestoreRecyclerOptions
                                .Builder<Orphanage>()
                                .setQuery(query, Orphanage::class.java).build()
        Orphadapter = OrphanageAdapter(recyclerViewOptions)

        binding.orphanageDisplayRv.adapter = Orphadapter
        binding.orphanageDisplayRv.layoutManager = LinearLayoutManager(this)
        //post_recycler_view.layoutManager = LinearLayoutManager(this)


    }

    override fun onStart() {
        super.onStart()
        Orphadapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        if (Orphadapter != null) {
            Orphadapter.stopListening()
        }
    }
}