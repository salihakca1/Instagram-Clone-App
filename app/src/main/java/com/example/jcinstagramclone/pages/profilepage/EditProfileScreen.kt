package com.example.jcinstagramclone.pages.profilepage

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jcinstagramclone.R
import com.example.jcinstagramclone.viewmodel.UserProfileDetailViewModel


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EditProfileScreen(navController: NavController){

    val name = remember { mutableStateOf("") }
    val userName = remember { mutableStateOf("") }
    val bio = remember { mutableStateOf("") }

    val context = LocalContext.current
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {
        imageUri = it
    }

    val viewModelUserProfile: UserProfileDetailViewModel = hiltViewModel()


    Scaffold(topBar =  {
        TopAppBar(
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(painter = painterResource(id = R.drawable.baseline_reply_24),
                        contentDescription = "",
                        modifier = Modifier.clickable {
                            navController.navigate("main_screen")
                        })
                    Spacer(modifier = Modifier.width(30.dp))
                    Text(text = "Profili Düzenle", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(120.dp))
                    Icon(painter = painterResource(id = R.drawable.baseline_done_24),
                        contentDescription = "",
                        modifier = Modifier.clickable {
                            viewModelUserProfile.updateProfileDetail(navController,imageUri!!, name.value, userName.value, bio.value)
                        })
                }
            },
            backgroundColor = Color.White,
        )
    }, content = {
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(80.dp))
            Column(modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                imageUri?.let {
                    if (Build.VERSION.SDK_INT < 28) {
                        bitmap.value = MediaStore.Images.Media.getBitmap(context.contentResolver, it)
                    } else {
                        val source = ImageDecoder.createSource(context.contentResolver, it)
                        bitmap.value = ImageDecoder.decodeBitmap(source)
                    }

                    bitmap.value?.let {
                        Image(
                            bitmap = it.asImageBitmap(), contentDescription = "", modifier = Modifier
                                .size(200.dp)
                                .padding(20.dp)
                        )
                    }
                }
                OutlinedButton(onClick = {
                    launcher.launch("image/*")
                },
                    shape =  CircleShape,
                    border = BorderStroke(1.dp, Color.Blue),
                    modifier = Modifier.size(150.dp, 65.dp)
                ) {
                    Text(text = "Resmi veya avatarı düzenle", fontSize = 18.sp)
                }
            }

            Spacer(modifier = Modifier.height(40.dp))
            Column(modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(value = name.value,
                    onValueChange = {name.value = it},
                    label = { Text(text = "Ad", fontSize = 20.sp, fontStyle = androidx.compose.ui.text.font.FontStyle.Italic)},
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
                    label = { Text(text = "Kullanıcı Ad", fontSize = 20.sp, fontStyle = androidx.compose.ui.text.font.FontStyle.Italic)},
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
                OutlinedTextField(value = bio.value,
                    onValueChange = {bio.value = it},
                    label = { Text(text = "Biyografi", fontSize = 20.sp, fontStyle = androidx.compose.ui.text.font.FontStyle.Italic)},
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.White,
                        textColor = Color.Black,
                        focusedLabelColor = Color.Black,
                        unfocusedLabelColor = Color.LightGray,
                        focusedBorderColor = Color.Gray,
                        unfocusedBorderColor = Color.Black,
                        cursorColor = Color.Gray
                    ))
            }

        }
    })

}