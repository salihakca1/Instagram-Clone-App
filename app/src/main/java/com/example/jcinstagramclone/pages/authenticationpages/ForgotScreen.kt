package com.example.jcinstagramclone.pages.authenticationpages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jcinstagramclone.R
import com.example.jcinstagramclone.viewmodel.AuthenticationViewModel

@Composable
fun Forgot(navController: NavController){
    val email = remember { mutableStateOf("") }
    val viewModel: AuthenticationViewModel = hiltViewModel()

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.lock), contentDescription = "")
        Spacer(modifier = Modifier.height(20.dp))
        Column(modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {

        Text(text = "Giriş Yaparken Sorun mu Yaşıyorsun?", textAlign = TextAlign.Center,fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(15.dp))
        Text(text = "E-posta adresini gir ve hesabına yeniden girebilmen için sana bir bağlantı gönderelim.", fontSize = 20.sp, color = Color.Gray, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(15.dp))
            OutlinedTextField(value = email.value,
                onValueChange = {email.value = it},
                label = { Text(text = "E-Posta giriniz",textAlign = TextAlign.Center, fontSize = 20.sp, fontStyle = FontStyle.Italic) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White,
                    textColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    unfocusedLabelColor = Color.LightGray,
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.Black,
                    cursorColor = Color.Gray
                ))
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedButton(onClick = {
                    viewModel.resetEmail(navController,email.value)
                    email.value = ""
            },
                shape =  CircleShape,
                border = BorderStroke(1.dp, Color.Blue),
                modifier = Modifier.size(300.dp, 65.dp)
            ) {
                Text(text = "Giriş Bağlantısını Gönder", fontSize = 20.sp )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Text(text = "------------------- YA DA ------------------------", textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(15.dp))
            OutlinedButton(onClick = {
                navController.navigate("sign_up_screen")
            },
                border = BorderStroke(1.dp, Color.White),
            ) {
                Text(text = "Yeni Hesap Oluştur", color = Color.Black, fontSize = 22.sp, fontStyle = androidx.compose.ui.text.font.FontStyle.Italic )
            }
        }
    }
}