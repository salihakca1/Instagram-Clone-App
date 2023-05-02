package com.example.jcinstagramclone.pages.profilepage

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jcinstagramclone.R
import com.example.jcinstagramclone.viewmodel.AuthenticationViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingScreen(navController: NavController){
    val openDialog = remember { mutableStateOf(false) }

    val viewModel: AuthenticationViewModel = hiltViewModel()

    Scaffold(topBar = {
        TopAppBar(title = {
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(painter = painterResource(id = R.drawable.baseline_reply_24), contentDescription = "",
                modifier = Modifier.clickable {
                    navController.navigate("main_screen")
                })
                Spacer(modifier = Modifier.width(20.dp))
                Text(text = "Ayarlar", fontSize = 22.sp, fontWeight = FontWeight.Bold)
            }
        },
            backgroundColor = Color.White,
        )
    },
    content = {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            horizontalAlignment = Alignment.Start
            ) {
            Spacer(modifier = Modifier.height(40.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.width(20.dp))
                Text(text = "Hesap Ayarları", fontSize = 22.sp, color = Color.Black, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(15.dp))
            OutlinedButton(onClick = {
                navController.navigate("change_password_screen")
            },
                border = BorderStroke(1.dp, Color.White)
            ) {
                Icon(painter = painterResource(id = R.drawable.baseline_key_24), contentDescription = "" )
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Şifre Değiştir", fontSize = 18.sp, color = Color.Black)
            }
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedButton(onClick = {
                    navController.navigate("change_email_screen")
            },
                border = BorderStroke(1.dp, Color.White)
            ) {
                Icon(painter = painterResource(id = R.drawable.baseline_email_24), contentDescription = "" )
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Email Değiştir", fontSize = 18.sp, color = Color.Black)
            }
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedButton(onClick = {
                        openDialog.value = true
            },
                border = BorderStroke(1.dp, Color.White)) {
                Icon(painter = painterResource(id = R.drawable.baseline_delete_24), contentDescription = "" )
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Hesabı Sil", fontSize = 18.sp, color = Color.Black)
            }
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedButton(onClick = {
                    viewModel.signOut(navController)
            },
                border = BorderStroke(1.dp, Color.White)
            ) {
                Icon(painter = painterResource(id = R.drawable.baseline_exit_to_app_24), contentDescription = "" )
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Çıkış Yap", fontSize = 18.sp, color = Color.Black)
            }
        }
    })

    if (openDialog.value){
        AlertDialog(
            onDismissRequest = { openDialog.value = false},
            title = { Text(text = "Hesap Sil", color = Color.White)},
            text = { Text(text = "Hesabınızı silmek istediğinizden emin misiniz?")},
            confirmButton = {
                Text(text = "Evet, eminim", color = Color.Red,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(all = 10.dp)
                        .clickable {
                            viewModel.deleteUser(navController)
                            openDialog.value = false
                        })
            },
            dismissButton = {
                Text(text = "Hayır", color = Color.Red,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(all = 10.dp)
                        .clickable {
                            openDialog.value = false
                        })
            },
            backgroundColor = Color.LightGray
        )
    }
}