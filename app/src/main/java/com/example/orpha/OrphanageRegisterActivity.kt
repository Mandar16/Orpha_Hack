package com.example.orpha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.orpha.Daos.OrphanageDaos
import com.example.orpha.databinding.ActivityOrphanageRegisterBinding
import com.example.orpha.models.Children
import com.example.orpha.models.Orphanage
import com.example.orpha.models.Users
import com.google.firebase.auth.FirebaseAuth

class OrphanageRegisterActivity : AppCompatActivity() {
    private lateinit var binding:ActivityOrphanageRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrphanageRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val list_of_issues : ArrayList<String>? =null
        val list_of_cofounders : ArrayList<String>? =null
        val list_of_childrens : ArrayList<Children>? = null
        val list_of_donors : ArrayList<Users>? = null

        binding.addBtnListofProblems.setOnClickListener {

            list_of_issues?.add(binding.listOfProblems.text.toString())
            binding.listOfProblems.setText("")

        }

        binding.addBtnListofCofounder.setOnClickListener {

            list_of_cofounders?.add(binding.listOfCoFounders.text.toString())
            binding.listOfCoFounders.setText("")
        }
        binding.orphanageSignUpBtn.setOnClickListener {

            val orphanage = Orphanage(
                binding.orphanageNameEditText.text.toString(),
                binding.addressEditText.text.toString(),
                list_of_issues,
                binding.noOfChildrenEditText.text.toString().toInt(),
                list_of_cofounders,
                binding.fundingDeficitEditText.text.toString().toInt(),
                list_of_childrens,
                list_of_donors
            )

            val user = FirebaseAuth.getInstance().currentUser

            val orphanageDaos = OrphanageDaos()

            orphanageDaos.addOrphanage(orphanage,user)



        }








    }
}