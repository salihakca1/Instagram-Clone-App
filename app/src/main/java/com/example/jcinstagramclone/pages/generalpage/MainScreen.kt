package com.example.jcinstagramclone.pages.generalpage

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.jcinstagramclone.R
import com.example.jcinstagramclone.pages.mainpage.PostScreen
import com.example.jcinstagramclone.pages.mainpage.StoryScreen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun MainScreen(navController: NavController){
    var selectedItem = remember { mutableStateOf(0) }

    Scaffold(
        topBar ={
            if (selectedItem.value == 0){
                TopAppBar(
                    title = {
                        Icon(painter = painterResource(id = R.drawable.instagram), contentDescription = "")
                    },
                    backgroundColor = Color.White,
                    actions = {
                        IconButton(onClick = {

                        }) {
                            Icon(painter = painterResource(id = R.drawable.baseline_favorite_border_24), contentDescription = "")
                        }
                        IconButton(onClick = {

                        }) {
                            Icon(painter = painterResource(id = R.drawable.baseline_chat_bubble_outline_24), contentDescription = "")
                        }
                    }
                )
            }
        },
        content = {
            if (selectedItem.value == 0){
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                   StoryScreen(navController)
                   PostScreen(navController)
                }
            }
            if (selectedItem.value == 1){
                SearchScreen()
            }
            if (selectedItem.value == 2) {
                ProfileScreen(navController = navController)
            }
        },
        bottomBar = {
            BottomAppBar(
            backgroundColor = Color.White,
                content = {
                BottomNavigation(backgroundColor = Color.White) {
                    BottomNavigationItem(selected = selectedItem.value == 0, onClick = {selectedItem.value = 0},
                        icon = {
                            Icon(painter = painterResource(id = R.drawable.baseline_home_24), contentDescription = "")
                        },
                        selectedContentColor = Color.Black,
                        unselectedContentColor = Color.Gray
                    )
                    BottomNavigationItem(selected = selectedItem.value == 1, onClick = {selectedItem.value = 1},
                        icon = {
                            Icon(painter = painterResource(id = R.drawable.baseline_search_24), contentDescription = "")
                        },
                        selectedContentColor = Color.Black,
                        unselectedContentColor = Color.Gray
                    )
                    BottomNavigationItem(selected = selectedItem.value == 2, onClick = {selectedItem.value = 2},
                        icon = {
                            Icon(painter = painterResource(id = R.drawable.baseline_person_pin_24), contentDescription = "")
                        },
                        selectedContentColor = Color.Black,
                        unselectedContentColor = Color.Gray
                    )
                }
                }
            )
        }

    )
}