package com.example.mangacollect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mangacollect.Screens.signUpScreen
import com.example.mangacollect.view_models.signUpViewModel

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val myViewModel = viewModel<signUpViewModel>()
            signUpScreen(myViewModel = myViewModel, context = this)
        }
    }
}
