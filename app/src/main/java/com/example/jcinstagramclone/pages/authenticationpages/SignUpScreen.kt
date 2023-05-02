package com.example.jcinstagramclone.pages.authenticationpages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jcinstagramclone.R
import com.example.jcinstagramclone.viewmodel.AuthenticationViewModel

@Composable
fun SignUp(navController: NavController){
    val userName = remember { mutableStateOf("") }
    val nameAndSurname = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = rememberSaveable{ mutableStateOf("") }

    val viewModel: AuthenticationViewModel = hiltViewModel()

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.instagram), contentDescription = "")
        Text(text = "Arkadaşlarının fotoğraf ve videolarını görmek için kayıt ol", fontSize = 20.sp, color = Color.Gray, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(15.dp))
        Column(modifier = Modifier
            .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(value = email.value,
                onValueChange = {email.value = it},
                label = { Text(text = "E-Posta", fontSize = 20.sp, fontStyle = FontStyle.Italic) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White,
                    textColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    unfocusedLabelColor = Color.LightGray,
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.Black,
                    cursorColor = Color.Gray
                ))
            Spacer(modifier = Modifier.height(3.dp))
            OutlinedTextField(value = nameAndSurname.value,
                onValueChange = {nameAndSurname.value = it},
                label = { Text(text = "Adı Soyadı", fontSize = 20.sp, fontStyle = FontStyle.Italic) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White,
                    textColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    unfocusedLabelColor = Color.LightGray,
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.Black,
                    cursorColor = Color.Gray
                ))
            Spacer(modifier = Modifier.height(3.dp))
            OutlinedTextField(value = userName.value,
                onValueChange = {userName.value = it},
                label = { Text(text = "Kullanıcı Adı", fontSize = 20.sp, fontStyle = FontStyle.Italic) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White,
                    textColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    unfocusedLabelColor = Color.LightGray,
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.Black,
                    cursorColor = Color.Gray
                ))
            Spacer(modifier = Modifier.height(3.dp))
            OutlinedTextField(value = password.value,
                onValueChange = {password.value = it},
                label = { Text(text = "Şifre ", fontSize = 20.sp, fontStyle = FontStyle.Italic) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White,
                    textColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    unfocusedLabelColor = Color.LightGray,
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.Black,
                    cursorColor = Color.Gray
                ),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            Spacer(modifier = Modifier.height(15.dp))
            OutlinedButton(onClick = {

                viewModel.signUpButton(navController,nameAndSurname = nameAndSurname.value, userName = userName.value, email = email.value, password = password.value)
                nameAndSurname.value = ""
                userName.value = ""
                email.value = ""
                password.value = ""

                },
                shape =  CircleShape,
                border = BorderStroke(1.dp, Color.Blue),
                modifier = Modifier.size(150.dp, 65.dp)
            ) {
                Text(text = "Kayıt Ol", fontSize = 20.sp )
            }
        }

    }
}