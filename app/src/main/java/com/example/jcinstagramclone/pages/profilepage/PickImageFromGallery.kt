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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jcinstagramclone.R
import com.example.jcinstagramclone.viewmodel.UploadViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PickImageFromGallery(navController: NavController) {

    val context = LocalContext.current
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }
    val description = remember { mutableStateOf("") }

    val viewModel: UploadViewModel = hiltViewModel()


    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {
        imageUri = it
    }
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
                    Text(text = "Yeni Gönderi", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(120.dp))
                    Icon(painter = painterResource(id = R.drawable.baseline_done_24),
                        contentDescription = "",
                        modifier = Modifier.clickable {
                            viewModel.uploadPost(imageUri!!, description.value, navController, true)
                        })
                }
            },
            backgroundColor = Color.White,
        )
    },content = {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
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
                            .size(400.dp)
                            .padding(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            OutlinedButton(onClick = {
                launcher.launch("image/*")
            },
                shape =  CircleShape,
                border = BorderStroke(1.dp, Color.Blue),
                modifier = Modifier.size(150.dp, 65.dp)
            ) {
                Text(text = "Fotoğraf Seç", fontSize = 18.sp)
            }

            OutlinedTextField(value = description.value,
                onValueChange = {description.value = it},
                label = { Text(text = "Açıklama Yaz..", fontSize = 20.sp, fontStyle = androidx.compose.ui.text.font.FontStyle.Italic)},
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
    )

}
