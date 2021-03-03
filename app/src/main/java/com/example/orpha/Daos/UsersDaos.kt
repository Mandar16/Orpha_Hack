package com.example.orpha.Daos

import com.example.orpha.models.Users
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UsersDaos {
    val db = FirebaseFirestore.getInstance()
    val usersCollection = db.collection("users")

    fun addUser(user:Users){
        user?.let {
            GlobalScope.launch {
                GlobalScope.launch(Dispatchers.IO){
                    user.uid?.let { it1 -> usersCollection.document(it1).set(it) }
                }
            }
        }
    }

    fun getUserById(uid:String): Task<DocumentSnapshot> {
        return usersCollection.document(uid).get()
    }

}