package com.example.jcinstagramclone.pages.generalpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jcinstagramclone.R
import com.example.jcinstagramclone.viewmodel.PostScreenViewModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun SearchScreen(){

    val viewModel: PostScreenViewModel = hiltViewModel()

    val allPostList = viewModel.allPostList.observeAsState(listOf())


    LazyVerticalGrid(
        columns = GridCells.Adaptive(130.dp),

        contentPadding = PaddingValues(1.dp),
        content = {
            items(allPostList.value.size) { index ->
                val post = allPostList.value!![index]

                Card(
                    backgroundColor = Color.White,
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth(),
                    elevation = 4.dp,
                ) {
                    GlideImage(imageModel = post.downloadUrl )
                }
            }
        }
    )
}