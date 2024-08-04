package com.example.mangacollect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mangacollect.Screens.loginScreen
import com.example.mangacollect.view_models.loginScreenViewModel

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val myViewModel = viewModel<loginScreenViewModel>()
            loginScreen(myViewModel , this , ::finishActivity)
        }

    }
    fun finishActivity(){
        finish()
    }
}

