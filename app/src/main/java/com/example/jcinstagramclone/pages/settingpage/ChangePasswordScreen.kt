package com.example.jcinstagramclone.pages.settingpage

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jcinstagramclone.R
import com.example.jcinstagramclone.viewmodel.AuthenticationViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ChangePasswordScreen(navController: NavController){

    val newPassword = remember { mutableStateOf("") }
    val newPasswordAgain = remember { mutableStateOf("") }

    val openDialog = remember { mutableStateOf(false)  }

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
                        if (newPassword == newPasswordAgain){
                            viewModel.changePassword(navController,newPassword.value)
                        }
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
            Text(text = "Şifren en az 6 karakter olmalı ve rakamlar, harfler ve özel karakterlerden (!$@%%) oluşmalıdır.", textAlign = TextAlign.Start)
            Spacer(modifier = Modifier.height(15.dp))
            Column(Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(5.dp))
                OutlinedTextField(value = newPassword.value,
                    onValueChange = {newPassword.value = it},
                    label = { Text(text = "Yeni Şifre", fontSize = 20.sp, fontStyle = androidx.compose.ui.text.font.FontStyle.Italic)},
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
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password))
                Spacer(modifier = Modifier.height(5.dp))
                OutlinedTextField(value = newPasswordAgain.value,
                    onValueChange = {newPasswordAgain.value = it},
                    label = { Text(text = "Yeni Şifre", fontSize = 20.sp, fontStyle = androidx.compose.ui.text.font.FontStyle.Italic)},
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
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedButton(onClick = {
                        openDialog.value = true
                },
                    border = BorderStroke(1.dp, Color.White)
                ) {
                    Text(text = "Şifreni mi Unuttun?", color = Color.Blue, fontSize = 19.sp, fontStyle = androidx.compose.ui.text.font.FontStyle.Italic )
                }
            }
        }
    }
    )
    if (openDialog.value){
        AlertDialog(onDismissRequest = { openDialog.value = false},
            title = { Text(
                text = "E- posta Gönderildi",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )},
            text ={ Text(text = "Mail adresine şifreni yenilemek için bağlantı içeren bir e-posta gönderdik.", fontSize = 17.sp)},
            confirmButton = {
                Text(text = "Tamam", textAlign = TextAlign.Center, color = Color.Red, fontWeight = FontWeight.Bold, modifier = Modifier.padding(all = 10.dp).clickable {
                    openDialog.value = false
                })
            },
            backgroundColor = Color.LightGray
        )
    }
}
