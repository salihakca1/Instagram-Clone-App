package com.example.jcinstagramclone.pages.settingpage

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
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

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ChangeEmailScreen(navController: NavController){
    val newEmail = remember { mutableStateOf("") }

    val viewModel: AuthenticationViewModel = hiltViewModel()

    Scaffold(topBar = {
        TopAppBar(title = {
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(painter = painterResource(id = R.drawable.baseline_reply_24), contentDescription = "",
                    modifier = Modifier.clickable {
                        navController.navigate("setting_screen")
                    })
                Spacer(modifier = Modifier.width(20.dp))
                Text(text = "Şifre", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(215.dp))
                Icon(painter = painterResource(id = R.drawable.baseline_done_24),
                    contentDescription = "",
                    modifier = Modifier.clickable {
                        viewModel.updateEmail(navController, newEmail.value)
                    })
            }
        },
            backgroundColor = Color.White,
        )
    }, content = {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(text = "Eğer E-mailinizi değiştirirseniz 14 gün içinde tekrardan E-mailinizi değiştiremeyecekseniz..", textAlign = TextAlign.Start)
            Spacer(modifier = Modifier.height(15.dp))
            Column(
                Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(5.dp))
                OutlinedTextField(value = newEmail.value,
                    onValueChange = {newEmail.value = it},
                    label = { Text(text = "Yeni E-mail", fontSize = 20.sp, fontStyle = FontStyle.Italic) },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.White,
                        textColor = Color.Black,
                        focusedLabelColor = Color.Black,
                        unfocusedLabelColor = Color.LightGray,
                        focusedBorderColor = Color.Gray,
                        unfocusedBorderColor = Color.Black,
                        cursorColor = Color.Gray
                    ))
                Spacer(modifier = Modifier.height(5.dp))


            }
        }
    }
    )
}