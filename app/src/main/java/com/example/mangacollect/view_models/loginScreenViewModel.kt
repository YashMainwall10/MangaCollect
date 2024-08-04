package com.example.mangacollect.view_models

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

class loginScreenViewModel : ViewModel() {
    var email  = mutableStateOf("")
    var password = mutableStateOf("")
}