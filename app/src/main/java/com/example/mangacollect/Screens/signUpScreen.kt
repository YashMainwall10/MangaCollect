package com.example.mangacollect.Screens

import android.content.Context
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mangacollect.LoginActivity
import com.example.mangacollect.ui.theme.CardColor
import com.example.mangacollect.view_models.signUpViewModel


class signUpScren {
}

@Composable
fun signUpScreen(myViewModel: signUpViewModel , createUser : (String , String , String)->Unit , toLoginActivity:()->Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 50.dp)
    ) {
        Card(
            colors = CardDefaults.cardColors(CardColor),
            elevation = CardDefaults.cardElevation(10.dp)
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp) ,
                    color = Color.White
                )

                mailText("Name", myViewModel.name, false)

                mailText("Email", myViewModel.email , false)

                mailText("Password", myViewModel.password , true)

                Box {
                    Column {
                        Button(onClick = {
                                         createUser(myViewModel.name.value , myViewModel.email.value , myViewModel.password.value)
                        },
                            modifier = Modifier.fillMaxWidth(1f)) {
                            Text(text = "Sign Up")
                        }
                        Text(text = "Already Have an account , Sing In Here !",
                            textAlign = TextAlign.Center,
                            fontSize = 14.sp,
                            color = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 15.dp , bottom = 15.dp)
                                .clickable {
                                    toLoginActivity()
                                })
                    }
                }
            }

        }

    }
}


