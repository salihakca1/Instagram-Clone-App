package com.example.jcinstagramclone.pages.mainpage

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jcinstagramclone.R
import com.example.jcinstagramclone.data.entity.Story
import com.example.jcinstagramclone.pages.authenticationpages.Login
import com.example.jcinstagramclone.viewmodel.StoryDetailViewModel
import com.example.jcinstagramclone.viewmodel.UserProfileDetailViewModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun StoryDetailScreen(storyId: String){

    val viewModel: StoryDetailViewModel = hiltViewModel()
    val storyList = viewModel.storyDetailList.observeAsState(listOf())

    LaunchedEffect(key1 = true){
        viewModel.readStoryDetail(storyId)
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(5.dp)
        ) {
            if (storyList.value.isEmpty()){
                GlideImage(
                    imageModel = R.drawable.baseline_lock_24,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }else{
                GlideImage(
                    imageModel = storyList.value.first().downloadUrl,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

        }
}