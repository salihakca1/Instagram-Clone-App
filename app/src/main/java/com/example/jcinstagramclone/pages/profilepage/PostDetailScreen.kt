package com.example.jcinstagramclone.pages.profilepage

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jcinstagramclone.R
import com.example.jcinstagramclone.viewmodel.PostDetailScreenViewModel
import com.skydoves.landscapist.glide.GlideImage

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PostDetailScreen(navController: NavController, postId: String){
    val viewModel: PostDetailScreenViewModel = hiltViewModel()
    val postList = viewModel.postDetailList.observeAsState(listOf())

    LaunchedEffect(key1 = true){
        viewModel.readPostDetail(postId)
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
                            navController.navigate("profile_screen")
                        })
                    Spacer(modifier = Modifier.width(30.dp))
                    Text(text = "Gönderiler", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(120.dp))

                }
            },
            backgroundColor = Color.White,
        )
    }, content = {

        Card(modifier = Modifier
            .padding(0.dp, 3.dp)
            .fillMaxSize(),
            border = BorderStroke(1.dp, Color.LightGray),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Spacer(modifier = Modifier.width(20.dp))
                    if (postList.value.isEmpty()){
                        GlideImage(imageModel = R.drawable.baseline_lock_24 ,
                            modifier = Modifier
                                .clip(shape = CircleShape)
                                .size(35.dp),
                            contentScale = ContentScale.Crop)
                    }else{
                        GlideImage(imageModel = postList.value.first().userImage ,
                            modifier = Modifier
                                .clip(shape = CircleShape)
                                .size(35.dp),
                            contentScale = ContentScale.Crop)
                    }
                    Spacer(modifier = Modifier.width(15.dp))
                    if (postList.value.isEmpty()){
                        Text(text = "", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                    }else{
                        Text(text = postList.value.first().userName, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                if (postList.value.isEmpty()){
                    GlideImage(imageModel = "" ,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(350.dp)
                    )
                }else{
                    GlideImage(imageModel = postList.value.first().downloadUrl ,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(500.dp)
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(35.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(20.dp))
                    Icon(painter = painterResource(id = R.drawable.baseline_favorite_border_24), contentDescription = "")
                    Spacer(modifier = Modifier.width(15.dp))
                    Icon(painter = painterResource(id = R.drawable.baseline_mode_comment_24), contentDescription = "")
                }
                Spacer(modifier = Modifier.height(1.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(47.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(20.dp))
                    if (postList.value.isEmpty()){
                        Text(text = "", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                    }else{
                        Text(text = postList.value.first().userName, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    if (postList.value.isEmpty()){
                        Text(text = "", fontSize = 15.sp)
                    }else{
                        Text(text = postList.value.first().description, fontSize = 15.sp)
                    }
                }
            }
        }
    })
}