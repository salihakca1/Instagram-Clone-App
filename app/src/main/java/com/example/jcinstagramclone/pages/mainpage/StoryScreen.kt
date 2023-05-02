package com.example.jcinstagramclone.pages.mainpage

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.example.jcinstagramclone.R
import com.example.jcinstagramclone.data.entity.Story
import com.example.jcinstagramclone.data.entity.User
import com.example.jcinstagramclone.viewmodel.AddStoryViewModel
import com.example.jcinstagramclone.viewmodel.UserProfileDetailViewModel
import com.google.gson.Gson
import com.skydoves.landscapist.glide.GlideImage

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun StoryScreen(navController: NavController){

    val viewModel: AddStoryViewModel = hiltViewModel()
    val storiesList = viewModel.storyList.observeAsState(listOf())
    val viewModelUserProfile: UserProfileDetailViewModel = hiltViewModel()
    val userProfileDetailList = viewModelUserProfile.profileDetailList.observeAsState(listOf())

    LazyRow{
        item{
            Card(modifier = Modifier
                .padding(2.dp, 3.dp)
                .size(100.dp, 85.dp)
            ) {
                OutlinedButton(onClick = {
                    navController.navigate("add_story")
                }) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (storiesList.value.isEmpty()){
                            GlideImage(imageModel = R.drawable.baseline_lock_24 ,
                                modifier = Modifier
                                    .clip(shape = CircleShape)
                                    .size(100.dp),
                                contentScale = ContentScale.Crop
                            )
                        } else{
                            GlideImage(
                                imageModel = userProfileDetailList.value.first().imageUrl ,
                                modifier = Modifier
                                    .clip(shape = CircleShape)
                                    .size(50.dp),
                                contentScale = ContentScale.Crop
                            )
                        }
                        Icon(painter = painterResource(id = R.drawable.baseline_add_circle_24), contentDescription ="" )
                    }
                }
            }

        }
        items(
            count = storiesList.value.size,
            itemContent = {
                val story = storiesList.value!![it]

                Card(modifier = Modifier
                    .padding(5.dp, 3.dp)
                    .size(100.dp, 85.dp),
                    border = BorderStroke(0.dp, Color.White)
                ) {
                    Row(modifier = Modifier.clickable {
                        val storyId = story.documentId
                        navController.navigate("story_detail_screen/$storyId")
                    }) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.SpaceEvenly,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            GlideImage(imageModel = story.downloadUrl ,
                                modifier = Modifier
                                    .clip(shape = CircleShape)
                                    .size(67.dp),
                                contentScale = ContentScale.Crop
                            )
                            Text(text = story.userName, fontSize = 13.sp)
                        }
                    }
                }
            }
        )
    }


        }


