package com.example.mangacollect.Screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mangacollect.MangaCollectionScreen
import com.example.mangacollect.SignUpActivity
import com.example.mangacollect.ui.theme.CardColor
import com.example.mangacollect.ui.theme.PurpleGrey80

import com.example.mangacollect.view_models.loginScreenViewModel
import com.google.firebase.auth.FirebaseAuth

class loginScreen {

}

@Composable
fun loginScreen(myViewModel: loginScreenViewModel , toSignUpActivity : ()->Unit , signInUser : (String , String)->Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 50.dp)
    ) {
        Card(
            elevation = CardDefaults.cardElevation(10.dp) ,
            colors = CardDefaults.cardColors(CardColor)
        ) {

            Column(
                verticalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .size(300.dp, 450.dp)
                    .padding(horizontal = 30.dp)
            ) {

                Text(
                    "Welcome", textAlign = TextAlign.Center,
                    fontSize = 32.sp, fontFamily = FontFamily.Cursive,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                )

                mailText("Email", myViewModel.email , false)

                mailText("Password", myViewModel.password , true)

                Box {
                    Column {
                        Button(onClick = {
                            signInUser(myViewModel.email.value , myViewModel.password.value)
                        },
                            modifier = Modifier.fillMaxWidth(1f)) {
                            Text(text = "Sign In")
                        }
                        Text(text = "Don't Have an account , Sing Up Here !",
                            textAlign = TextAlign.Center,
                            fontSize = 14.sp,
                            color = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 15.dp)
                                .clickable {
                                    toSignUpActivity()
                                })
                    }
                }
            }

        }

    }
}


@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true , showSystemUi = true)
@Composable
fun test(){
    var str = mutableStateOf("")
    mailText(Typo = "Hello", str = str, pass = false)
}


@Composable
fun mailText(Typo: String, str: MutableState<String> , pass : Boolean) {
        OutlinedTextField(value = str.value,
            onValueChange = {
                str.value = it
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedTextColor = Color.White,
                focusedTextColor = Color.White
            ),
            singleLine = true,
            label = { Text(Typo, fontSize = 12.sp , color = Color.White) },
            textStyle = TextStyle(fontSize = 15.sp),
            modifier = Modifier.padding(end = 5.dp , start = 5.dp , bottom = 15.dp),
            visualTransformation = if(pass){
                PasswordVisualTransformation()
            }
            else{
                VisualTransformation.None
            },

        )
}

