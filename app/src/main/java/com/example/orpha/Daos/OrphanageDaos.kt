package com.example.orpha.Daos

import com.example.orpha.models.Orphanage
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class OrphanageDaos {

    val db = FirebaseFirestore.getInstance()
    val orphanageCollections = db.collection("orphanages")

    fun addOrphanage(orphanage:Orphanage, user: FirebaseUser?){
        orphanage?.let {
            GlobalScope.launch(Dispatchers.IO) {
                if (user != null) {
                    user.uid?.let { it1 -> orphanageCollections.document(it1).set(it) }
                }
            }
        }
    }



}