package com.example.orpha

import android.R
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.orpha.Daos.OrphanageDaos
import com.example.orpha.adapter.IPostAdapter
import com.example.orpha.adapter.OrphanageAdapter
import com.example.orpha.databinding.ActivityOrphanageDiaplayBinding
import com.example.orpha.models.Orphanage
import com.firebase.ui.firestore.FirestoreRecyclerOptions


class OrphanageDisplayActivity : AppCompatActivity(), IPostAdapter {
    lateinit var  Orphadapter:OrphanageAdapter

    private var mTopToolbar: Toolbar? = null
    private lateinit var binding:ActivityOrphanageDiaplayBinding
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
        Orphadapter = OrphanageAdapter(recyclerViewOptions, this)

        binding.orphanageDisplayRv.adapter = Orphadapter
        binding.orphanageDisplayRv.layoutManager = LinearLayoutManager(this)

    }


    override fun onStart() {
        super.onStart()
        Orphadapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        Orphadapter.stopListening()
    }

    override fun onLikeClicked(id: String) {
       //Toast.makeText(this,"documentId->"+id,Toast.LENGTH_SHORT).show()

        val intent = Intent(this, OrphanageDetailsActivity::class.java).putExtra("documentId", id)
        startActivity(intent)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        intent= Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(com.example.orpha.R.menu.backbtn, menu);
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()

        return super.onOptionsItemSelected(item)
    }
}