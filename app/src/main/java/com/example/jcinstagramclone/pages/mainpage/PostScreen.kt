package com.example.jcinstagramclone.pages.mainpage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.example.jcinstagramclone.viewmodel.AddStoryViewModel
import com.example.jcinstagramclone.viewmodel.PostScreenViewModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun PostScreen(navController: NavController){
    val viewModel: PostScreenViewModel = hiltViewModel()
    val allPostList = viewModel.allPostList.observeAsState(listOf())


    LazyColumn{
        items(
            count = allPostList.value.size,
            itemContent = {
                val post = allPostList.value!![it]

                Card(modifier = Modifier
                    .padding(0.dp, 3.dp)
                    .fillMaxWidth()
                    .height(508.dp),
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
                                .height(40.dp).clickable {

                                },
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Spacer(modifier = Modifier.width(20.dp))
                            GlideImage(imageModel = post.userImage ,
                                modifier = Modifier
                                    .clip(shape = CircleShape)
                                    .size(35.dp),
                                contentScale = ContentScale.Crop)
                            Spacer(modifier = Modifier.width(15.dp))
                            Text(text = post.userName, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        GlideImage(imageModel = post.downloadUrl ,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(350.dp)
                        )
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
                            Text(text = post.userName, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = post.description, fontSize = 15.sp)
                        }
                    }
                }
            }
        )
        item { 
            Spacer(modifier = Modifier.height(53.dp))
        }
    }
}