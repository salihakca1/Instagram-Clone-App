package com.example.jcinstagramclone.pages.generalpage

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import com.example.jcinstagramclone.viewmodel.ProfilePostViewModel
import com.example.jcinstagramclone.viewmodel.UserProfileDetailViewModel
import com.skydoves.landscapist.glide.GlideImage

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileScreen(navController: NavController){

    val viewModelUserProfile: UserProfileDetailViewModel = hiltViewModel()
    val viewModelPost: ProfilePostViewModel = hiltViewModel()

    val userProfilePostList = viewModelPost.profilePostList.observeAsState(listOf())
    val userProfileDetailList = viewModelUserProfile.profileDetailList.observeAsState(listOf())

    LaunchedEffect(key1 = true){
        viewModelUserProfile.readProfileDetail()
    }

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(painter = painterResource(id = R.drawable.baseline_lock_24), contentDescription = "")
                    Spacer(modifier = Modifier.width(3.dp))
                    if (userProfileDetailList.value.isEmpty()){
                        Text(text = "" , fontSize = 20.sp, fontWeight = FontWeight.Bold)

                    }else{
                        Text(text = userProfileDetailList.value.first().userName , fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    }
                }

            },
            backgroundColor = Color.White,
            actions = {
                IconButton(onClick = {
                        navController.navigate("add_post")
                }) {
                    Icon(painter = painterResource(id = R.drawable.baseline_add_circle_outline_24), contentDescription = "")
                }
                IconButton(onClick = {
                        navController.navigate("setting_screen")
                }) {
                    Icon(painter = painterResource(id = R.drawable.baseline_settings_24), contentDescription = "")
                }
            }
        )
    }, content = {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(15.dp))
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Spacer(modifier = Modifier.width(25.dp))
                if (userProfileDetailList.value.isEmpty()){
                    GlideImage(imageModel = R.drawable.baseline_lock_24 ,
                        modifier = Modifier
                            .clip(shape = CircleShape)
                            .size(100.dp),
                        contentScale = ContentScale.Crop
                    )
                }else{
                    GlideImage(imageModel = userProfileDetailList.value.first().imageUrl,
                        modifier = Modifier
                            .clip(shape = CircleShape)
                            .size(100.dp),
                        contentScale = ContentScale.Crop
                    )
                }


                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment =Alignment.CenterHorizontally
                ) {
                    Text(text = "    ${userProfilePostList.value.size}          404        500", fontSize = 17.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(text = "Gönderi - Takipçi - Takip", fontSize = 17.sp)
                }
            }
            Spacer(modifier = Modifier.height(3.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(3.dp)
            ) {
                Spacer(modifier = Modifier.width(15.dp))
                if (userProfileDetailList.value.isEmpty()){
                    Text(text = "" , fontSize = 20.sp, fontWeight = FontWeight.Bold)

                }else{
                    Text(text = userProfileDetailList.value.first().name, fontSize = 17.sp, fontWeight = FontWeight.Bold)
                }
            }
            Spacer(modifier = Modifier.height(0.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(3.dp)) {
                Spacer(modifier = Modifier.width(15.dp))
                if (userProfileDetailList.value.isEmpty()){
                    Text(text = "" , fontSize = 20.sp, fontWeight = FontWeight.Bold)

                }else{
                    Text(text = userProfileDetailList.value.first().bio, fontSize = 17.sp)
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(3.dp)
            ) {
                Spacer(modifier = Modifier.width(20.dp))
                OutlinedButton(onClick = {
                    navController.navigate("edit_profile_screen")
                },
                    shape =  CircleShape,
                    border = BorderStroke(1.dp, Color.Gray),
                    modifier = Modifier.size(250.dp, 50.dp)
                ) {
                    Icon(painter = painterResource(id = R.drawable.baseline_edit_24), contentDescription = "")
                    Text(text = "Profili Düzenle", fontSize = 14.sp, fontWeight = FontWeight.Bold )
                }
            }

            LazyVerticalGrid(
                columns = GridCells.Adaptive(130.dp),

                contentPadding = PaddingValues(1.dp),
                content = {
                    items(userProfilePostList.value.size) {
                        val post = userProfilePostList.value!![it]
                        Card(
                            backgroundColor = Color.White,
                            modifier = Modifier
                                .padding(3.dp)
                                .fillMaxWidth().clickable {
                                    val postId = post.documentId
                                    navController.navigate("post_detail_screen/$postId")
                                },
                            elevation = 4.dp,
                        ) {
                            GlideImage(imageModel = post.downloadUrl ,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(15.dp)))
                        }
                    }
                }
            )

        }
    })
}