package com.example.mangacollect

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mangacollect.Screens.loginScreen
import com.example.mangacollect.collections.fireBaseInstances
import com.example.mangacollect.view_models.loginScreenViewModel

class LoginActivity : ComponentActivity() {
    val firebaseAuth = fireBaseInstances.getFirebaseAuthInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val myViewModel = viewModel<loginScreenViewModel>()
            loginScreen(myViewModel , ::toSignUpActivity , ::singInUser)
        }

    }
    private fun singInUser(email : String , password : String){
        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this@LoginActivity ,"Fill all the Fields" , Toast.LENGTH_LONG).show()
            return
        }
        firebaseAuth.signInWithEmailAndPassword(email , password).addOnSuccessListener {
            Toast.makeText(this@LoginActivity ,"Login Successful" , Toast.LENGTH_LONG).show()
            val intent = Intent(this@LoginActivity , MangaCollectionScreen::class.java)
            startActivity(intent)
            finish()
        }.addOnFailureListener {
            Toast.makeText(this@LoginActivity ,"Invalid Credentials" , Toast.LENGTH_LONG).show()
        }

    }
    private fun toSignUpActivity(){
        val intent = Intent(this@LoginActivity , SignUpActivity::class.java)
        startActivity(intent)
        finish()
    }
}





