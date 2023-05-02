package com.example.jcinstagramclone.pages.authenticationpages

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jcinstagramclone.R
import com.example.jcinstagramclone.viewmodel.AuthenticationViewModel

@SuppressLint("SuspiciousIndentation")
@Composable
fun Login(navController: NavController){
           val email = remember { mutableStateOf("") }
           val password = rememberSaveable{ mutableStateOf("") }

           val viewModel: AuthenticationViewModel = hiltViewModel()

           viewModel.getCurrentUser(navController)



    Column(modifier = Modifier.fillMaxSize(),
               verticalArrangement = Arrangement.Center,
               horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(painter = painterResource(id = R.drawable.instagram), contentDescription = "")

            Column(modifier = Modifier
                .fillMaxWidth(),
                   verticalArrangement = Arrangement.SpaceEvenly,
                   horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(value = email.value,
                    onValueChange = {email.value = it},
                    label = { Text(text = "E-Posta giriniz", fontSize = 20.sp, fontStyle = androidx.compose.ui.text.font.FontStyle.Italic)},
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.White,
                        textColor = Color.Black,
                        focusedLabelColor = Color.Black,
                        unfocusedLabelColor = Color.LightGray,
                        focusedBorderColor = Color.Gray,
                        unfocusedBorderColor = Color.Black,
                        cursorColor = Color.Gray
                    ))
                Spacer(modifier = Modifier.height(25.dp))
                OutlinedTextField(value = password.value,
                    onValueChange = {password.value = it},
                    label = { Text(text = "Şifre giriniz", fontSize = 20.sp, fontStyle = androidx.compose.ui.text.font.FontStyle.Italic)},
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
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedButton(onClick = {
                    navController.navigate("forgot_screen")
                },
                    border = BorderStroke(1.dp, Color.White)
                ) {
                    Text(text = "Şifreni mi Unuttun?", color = Color.Blue, fontSize = 19.sp, fontStyle = androidx.compose.ui.text.font.FontStyle.Italic )
                }
                Spacer(modifier = Modifier.height(15.dp))
                OutlinedButton(onClick = {
                    viewModel.logInButton(navController,email.value, password.value)
                    email.value = ""
                    password.value = ""
            },
                shape =  CircleShape,
                border = BorderStroke(1.dp, Color.Blue),
                modifier = Modifier.size(150.dp, 65.dp)
            ) {
                Text(text = "Giriş Yap", fontSize = 20.sp )
            }
                Spacer(modifier = Modifier.height(15.dp))
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically) {
                    Text(text ="Hesabın yok mu?", fontSize = 19.sp,fontStyle = androidx.compose.ui.text.font.FontStyle.Italic, color = Color.Gray)
                    OutlinedButton(onClick = {
                             navController.navigate("sign_up_screen")
                    },
                        border = BorderStroke(1.dp, Color.White),
                    ) {
                        Text(text = "Kayıt Ol",color = Color.Blue,fontSize = 19.sp, fontStyle = androidx.compose.ui.text.font.FontStyle.Italic )
                    }
                }
            }

        }
}

