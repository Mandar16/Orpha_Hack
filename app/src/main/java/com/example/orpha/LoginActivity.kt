package com.example.orpha

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.orpha.Daos.UsersDaos
import com.example.orpha.databinding.ActivityLoginBinding
import com.example.orpha.models.Users
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    lateinit var gso : GoogleSignInOptions
    private lateinit var googleSignInClient : GoogleSignInClient
    private val firebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }
   private val usermode  =  Users()

   private val RC_SIGN_IN =1234

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        // Configure Google Sign In
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        binding.signInBtn.setOnClickListener {
            signIn()
        }
    }

    override fun onStart() {
        super.onStart()
        if(firebaseAuth.currentUser != null){
            updateUI(firebaseAuth.currentUser)
        }
    }

    private fun signIn() {
        googleSignInClient = GoogleSignIn.getClient(this,gso)
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Toast.makeText(this,"RC_SIGN_IN CHECKED", Toast.LENGTH_SHORT).show()
            if(resultCode == RESULT_OK){
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                HandleSignIn(task)
            }
            else{
                Toast.makeText(this,"Again went to else block", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun HandleSignIn(task: Task<GoogleSignInAccount>?) {

        try {
            // Google Sign In was successful, authenticate with Firebase
            val account = task?.getResult(ApiException::class.java)!!
            //Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
            firebaseAuthWithGoogle(account.idToken!!)
        } catch (e: ApiException) {
            Toast.makeText(this,"Again went to else block",Toast.LENGTH_SHORT).show()
            Log.d("qwerty", "api exception")
            // Google Sign In failed, update UI appropriately
            //Log.w(TAG, "Google sign in failed", e)
            // ...
        }

    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    //Log.d(TAG, "signInWithCredential:success")
                    val user = firebaseAuth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                   //Log.w(TAG, "signInWithCredential:failure", task.exception)
                    // ...
                   // Snackbar.make(view, "Authentication Failed.", Snackbar.LENGTH_SHORT).show()
                    updateUI(null)
                }

                // ...
            }

    }

    private fun updateUI(firebaseUser: FirebaseUser?) {

        if(firebaseUser != null){
         val user =   Users(
                firebaseUser.displayName,
                firebaseUser.uid,
                firebaseUser.photoUrl.toString()
            )

            val userDaos = UsersDaos()
            userDaos.addUser(user)
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
            Toast.makeText(this,"Sucessfullsignin",Toast.LENGTH_SHORT).show()

        }

    }
}