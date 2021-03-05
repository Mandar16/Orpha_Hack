package com.example.orpha

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.orpha.Daos.OrphanageDaos
import com.example.orpha.adapter.ChildrenAdapter
import com.example.orpha.adapter.CoFounderAdapter
import com.example.orpha.adapter.IssuesAdapter
import com.example.orpha.databinding.ActivityOrphanageRegisterBinding
import com.example.orpha.models.Children
import com.example.orpha.models.Orphanage
import com.example.orpha.models.Users
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask


class OrphanageRegisterActivity : AppCompatActivity() {
    private lateinit var binding:ActivityOrphanageRegisterBinding

   lateinit var  issueadapter : IssuesAdapter
   lateinit var  coFoundersadapter :CoFounderAdapter
   //lateinit var donorsadapter :DonorsAdapter
   private val RC_FOR_IMG = 1234

    val storage by lazy {
        FirebaseStorage.getInstance()
    }
    val auth by lazy {
        FirebaseAuth.getInstance()

    }
   var childImageUrl:String = ""
   var nameOfChild:String = ""
   var ageOFChild:Int = 0


   var children: Children = Children()

    val list_of_issues : ArrayList<String> = ArrayList()
    val list_of_cofounders : ArrayList<String> = ArrayList()
    val list_of_childrens : ArrayList<Children> = ArrayList()
    val list_of_donors : ArrayList<Users> = ArrayList()


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrphanageRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.addBtnListofProblems.setOnClickListener {

            list_of_issues.add(binding.listOfProblems.text.toString())
            binding.listOfProblems.setText("")
            Toast.makeText(this, binding.listOfProblems.text.toString(), Toast.LENGTH_LONG).show()
            Toast.makeText(this, list_of_issues.size.toString(), Toast.LENGTH_LONG).show()

        }

        binding.addBtnListofCofounder.setOnClickListener {
            list_of_cofounders.add(binding.listOfCoFounders.text.toString())
            binding.listOfCoFounders.setText("")
        }


        //seting up Issues Adapter
        issueadapter = IssuesAdapter(list_of_issues)
        binding.listOfProblemsRv.adapter = issueadapter
        binding.listOfProblemsRv.layoutManager = LinearLayoutManager(this)

        //SettingUp CoFoundersAdapter
        coFoundersadapter = CoFounderAdapter(list_of_cofounders)
        binding.listOfCoFoundersRv.adapter = coFoundersadapter
        binding.listOfCoFoundersRv.layoutManager = LinearLayoutManager(this)

        binding.childImage.setOnClickListener {
            checkforpermission()
        }

        binding.childAddBtn.setOnClickListener {
            nameOfChild  = binding.nameOfChildrenEditText.text.toString()
            ageOFChild =  binding.ageOfChildrenEditText.text.toString().toInt()
             children = Children(
                binding.nameOfChildrenEditText.text.toString(),
                binding.ageOfChildrenEditText.text.toString().toInt(),

                     )
            Toast.makeText(this,"AFTER ClickChildBTN ->"+childImageUrl,Toast.LENGTH_SHORT).show()


            binding.nameOfChildrenEditText.setText("")
            binding.ageOfChildrenEditText.setText("")
            binding.childImage.setImageResource(R.drawable.ic_launcher_foreground)

        }

        //SettingUp Children detailsAdapter
        binding.childDetailsRv.layoutManager = LinearLayoutManager(this)
        val childrenAdapter= ChildrenAdapter(list_of_childrens)
        binding.childDetailsRv.adapter=childrenAdapter


        binding.orphanageSignUpBtn.setOnClickListener {
            val orphanage = Orphanage(
                binding.orphanageNameEditText.text.toString(),
                binding.addressEditText.text.toString(),
                binding.contactNoEditText.text.toString(),
                binding.emailEditText.text.toString(),
                binding.upiIdEdtText.text.toString(),
                list_of_issues,
                binding.noOfChildrenEditText.text.toString().toInt(),
                list_of_cofounders,
                binding.fundingDeficitEditText.text.toString().toInt(),
                list_of_childrens,
                list_of_donors
            )

            val user = FirebaseAuth.getInstance().currentUser
            val orphanageDaos = OrphanageDaos()
            orphanageDaos.addOrphanage(orphanage, user)

            Toast.makeText(this,"SignUp successfully" , Toast.LENGTH_SHORT).show()
            intent = Intent(this,OrphanageDiaplayActivity::class.java)
            startActivity(intent)
            finish()

        }



    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun checkforpermission() {
        if((checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
                &&(checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED)){

            val permission = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            val permissionWrite = arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

            requestPermissions(
                    permission,1001
            )
            requestPermissions(permissionWrite,1002)

        }else{
            pickImageFromGallery()
        }
    }

    private fun pickImageFromGallery() {

        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"

        startActivityForResult(intent,RC_FOR_IMG)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK){
            data?.data?.let{
                binding.childImage.setImageURI(it)
                uploadImage(it)
            }
        }
    }

    private fun uploadImage(it: Uri) {

        val ref = storage.reference.child("upload/"+ auth.uid.toString()+list_of_childrens.size.toString())
        val uploadTask = ref.putFile(it)
        uploadTask.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>>{ task ->
            if(!task.isSuccessful){
                task.exception?.let { t->
                    throw t
                }
            }
            return@Continuation ref.downloadUrl

        }).addOnCompleteListener { task->


            if(task.isSuccessful){
                childImageUrl = task.result.toString()
                children = Children(nameOfChild,ageOFChild,childImageUrl)
                list_of_childrens.add(children)
               Toast.makeText(this,"AFTER TASK ->"+childImageUrl,Toast.LENGTH_LONG).show()
            }else{

            }

        }.addOnFailureListener {

        }

    }

}