package com.example.orpha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.orpha.Daos.OrphanageDaos
import com.example.orpha.adapter.DonorsAdapter
import com.example.orpha.databinding.ActivityDonorsListRvBinding
import com.example.orpha.models.Orphanage
import com.example.orpha.models.Users
import com.firebase.ui.auth.data.model.User

class DonorsListRv : AppCompatActivity() {
    private lateinit var binding:ActivityDonorsListRvBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDonorsListRvBinding.inflate(layoutInflater)
        var listOfDonors:ArrayList<Users> = ArrayList()
        val view = binding.root
        setContentView(view)
        //setContentView(R.layout.activity_donors_list_rv)
        val user1  = Users("Mandar","1234")
        val user2 = Users("Tanmay","2345")
        val user3 = Users("Binks" , "2567")

        listOfDonors.add(user1)
        listOfDonors.add(user3)
        listOfDonors.add(user2)

        val donorsListAdapter = DonorsAdapter(listOfDonors)
        binding.donorsListActivityRv.adapter = donorsListAdapter
        binding.donorsListActivityRv.layoutManager = LinearLayoutManager(this)


        val documentId:String? = intent.getStringExtra("documentId")
        val orphanageDaos = OrphanageDaos()

        if (documentId != null) {
            orphanageDaos.getOrphanage(documentId).addOnSuccessListener {
                val orphanage = it.toObject(Orphanage::class.java)
                if (orphanage != null) {
                    listOfDonors = orphanage.list_of_donors!!
                }

//               val donorsListAdapter = DonorsAdapter(listOfDonors)
//                binding.donorsListActivityRv.adapter = donorsListAdapter
//                binding.donorsListActivityRv.layoutManager = LinearLayoutManager(this)


            }
        }
    }
}