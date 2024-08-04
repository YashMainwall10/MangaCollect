package com.example.mangacollect.view_models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel


class signUpViewModel : ViewModel(){
    var name = mutableStateOf("")
    var email = mutableStateOf("")
    var password = mutableStateOf("")
}