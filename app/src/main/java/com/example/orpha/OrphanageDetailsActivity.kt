package com.example.orpha

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.orpha.Daos.OrphanageDaos
import com.example.orpha.adapter.CoFounderAdapter
import com.example.orpha.adapter.IssuesAdapter
import com.example.orpha.databinding.ActivityOrphanageDetailsBinding
import com.example.orpha.databinding.ActivityOrphanageDiaplayBinding
import com.example.orpha.models.Orphanage

class OrphanageDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrphanageDetailsBinding
    lateinit var  issueadapter : IssuesAdapter
    lateinit var  coFoundersadapter : CoFounderAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrphanageDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
       // setContentView(R.layout.activity_orphanage_details)

        val documentId: String? = intent.getStringExtra("documentId")
        Toast.makeText(this,"DocumentID =>" + documentId,Toast.LENGTH_SHORT).show()





       val orphanageDaos = OrphanageDaos()
        if (documentId != null) {
            orphanageDaos.getOrphanage(documentId).addOnSuccessListener {
                val orphanage  = it.toObject(Orphanage::class.java)!!

                Toast.makeText(this,"Orphanage Name = > "+orphanage.name,Toast.LENGTH_SHORT).show()

                binding.orphaNametv.text = orphanage.name
                binding.noOfOrphansTv.text = "Number Of Childrens"+orphanage.no_of_children.toString()
                binding.orphaAddressTv.text = "Address" + orphanage.address

                //Setting up List of Problems RecyclerView
                issueadapter = orphanage.issues?.let { IssuesAdapter(it) }!!
                binding.listOfProblemsRv.adapter = issueadapter
                binding.listOfProblemsRv.layoutManager = LinearLayoutManager(this)

                //Setting up List of Cofounders
                coFoundersadapter = orphanage.founders?.let { CoFounderAdapter(it) }!!
                binding.listOfCoFoundersRv.adapter = coFoundersadapter
                binding.listOfCoFoundersRv.layoutManager = LinearLayoutManager(this)


                binding.fundingDeficitTv.text = "Funding Required"+orphanage.funding_deficit.toString()
                binding.orphaContactTv.text = orphanage.phoneNumber.toString()
                binding.orphaEmailTv.text = orphanage.email

                binding.listOfOrphansTv.setOnClickListener {

                }








                binding.donateNowBtn.setOnClickListener {
                    val intent = Intent(this,DonationActivity::class.java)
                    intent.putExtra("UPI_ID",orphanage.upiid)
                    // intent.putExtra("",orphanage)
                    startActivity(intent)
                    finish()
                }

            }
        }





    }
}