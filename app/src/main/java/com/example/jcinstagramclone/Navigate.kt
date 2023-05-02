package com.example.jcinstagramclone

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jcinstagramclone.pages.generalpage.MainScreen
import com.example.jcinstagramclone.pages.authenticationpages.Forgot
import com.example.jcinstagramclone.pages.authenticationpages.Login
import com.example.jcinstagramclone.pages.authenticationpages.SignUp
import com.example.jcinstagramclone.pages.generalpage.ProfileScreen
import com.example.jcinstagramclone.pages.mainpage.AddStory
import com.example.jcinstagramclone.pages.mainpage.StoryDetailScreen
import com.example.jcinstagramclone.pages.profilepage.EditProfileScreen
import com.example.jcinstagramclone.pages.profilepage.SettingScreen
import com.example.jcinstagramclone.pages.settingpage.ChangeEmailScreen
import com.example.jcinstagramclone.pages.settingpage.ChangePasswordScreen
import com.example.jcinstagramclone.pages.profilepage.PickImageFromGallery
import com.example.jcinstagramclone.pages.profilepage.PostDetailScreen

@Composable
fun Navigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login_screen"){
        composable("login_screen"){
            Login(navController)
        }
        composable("sign_up_screen"){
            SignUp(navController)
        }
        composable("forgot_screen"){
            Forgot(navController)
        }
        composable("main_screen"){
            MainScreen(navController)
        }
        composable("edit_profile_screen"){
            EditProfileScreen(navController)
        }
        composable("profile_screen"){
                ProfileScreen(navController = navController)
        }
        composable("setting_screen"){
            SettingScreen(navController = navController)
        }
        composable("change_password_screen"){
            ChangePasswordScreen(navController)
        }
        composable("change_email_screen"){
            ChangeEmailScreen(navController = navController)
        }
        composable("add_post"){
            PickImageFromGallery(navController)
        }
        composable("add_story"){
            AddStory(navController = navController)
        }
        composable("story_detail_screen/{storyId}", arguments = listOf(
            navArgument("storyId"){type = NavType.StringType}
        )){
            val story = it.arguments?.getString("storyId")
            StoryDetailScreen(storyId = story.toString())
        }
        composable("post_detail_screen/{postId}", arguments = listOf(
            navArgument("postId"){type = NavType.StringType}
        )){
            val post = it.arguments?.getString("postId")
            PostDetailScreen(navController = navController, post.toString())
        }


    }
}