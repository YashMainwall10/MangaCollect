package com.example.mangacollect

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mangacollect.Screens.signUpScreen
import com.example.mangacollect.collections.fireBaseInstances
import com.example.mangacollect.view_models.signUpViewModel
import com.google.android.play.integrity.internal.f
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : ComponentActivity() {
    val firebaseAuth = fireBaseInstances.getFirebaseAuthInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val myViewModel = viewModel<signUpViewModel>()
            signUpScreen(myViewModel = myViewModel, ::createUser , ::toLoginInActivity)
        }
    }
    private fun createUser(name : String , email : String , password : String){
        if(name.isEmpty() || email.isEmpty() || password.isEmpty()){
            Toast.makeText(this@SignUpActivity , "Fill all the fields" , Toast.LENGTH_LONG).show()
            return
        }
        firebaseAuth.createUserWithEmailAndPassword(email , password).addOnSuccessListener {
            Toast.makeText(this@SignUpActivity , "User Created" , Toast.LENGTH_LONG).show()
        }.addOnFailureListener {
            Toast.makeText(this@SignUpActivity , "Something Went Wrong" , Toast.LENGTH_LONG).show()
        }
    }
    private fun toLoginInActivity(){
        val intent = Intent(this@SignUpActivity , LoginActivity::class.java)
        startActivity(intent)
        finish()
    }



}
