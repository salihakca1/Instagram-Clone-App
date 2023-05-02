package com.example.jcinstagramclone.data.repo


import androidx.navigation.NavController
import com.example.jcinstagramclone.data.entity.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*
import javax.inject.Inject

class AuthDaoRepository @Inject constructor(var auth: FirebaseAuth, var fireStore: FirebaseFirestore) {

        fun signUpClick(navController: NavController,email: String, password: String, userName: String, name: String){

                    try {
                        if (email.isNotEmpty() && password.isNotEmpty() && userName.isNotEmpty() ){
                            auth.createUserWithEmailAndPassword(email,password)
                                .addOnSuccessListener {
                                    val userId = auth.currentUser?.uid.toString()

                                    val user = User(userId = userId, userName = userName, name = name, email = email, password = password)
                                    fireStore.collection("users").document(userId).set(user).addOnSuccessListener {
                                        navController.navigate("login_screen")
                                    }.addOnFailureListener {

                                    }
                                }.addOnFailureListener {
                                }
                        }
                    }catch (_:Exception){ }
        }

        fun logInClick(navController: NavController, email: String, password: String){
                    try {
                        if (email.isNotEmpty() && password.isNotEmpty()){
                            auth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener {
                                    if (it.isSuccessful) {
                                        auth.currentUser?.reload()
                                        navController.navigate("main_screen"){
                                            popUpTo("login_screen"){inclusive = true}
                                        }
                                    }
                                }
                        }
                    }catch (_: Exception){ }

                }



        fun sendPasswordResetEmail(navController: NavController, email: String){
                    try {
                        if (email.isNotEmpty()){
                            Firebase.auth.sendPasswordResetEmail(email)
                                .addOnCompleteListener {
                                    if (it.isSuccessful){
                                        navController.navigate("login_screen")
                                    }
                                }
                        }
                    }catch (_: Exception){ }

                }


        fun getCurrentUser(navController: NavController){
                    try {
                        val user = Firebase.auth.currentUser
                        if (user != null){
                            navController.navigate("main_screen")
                        }
                    }catch (e: Exception){}
                }


        fun settingFragmentChangePassword(navController: NavController,newPassword: String){
                    try {
                        val user =  Firebase.auth.currentUser
                        user!!.updatePassword(newPassword)
                            .addOnCompleteListener {
                                if (it.isSuccessful){
                                    navController.navigate("setting_screen")
                                }
                            }
                    }catch (e: Exception){}


        }

        fun settingFragmentUserDelete(navController: NavController){
                    try {
                        val user  = Firebase.auth.currentUser

                        user!!.delete()
                            .addOnCompleteListener {
                                if (it.isSuccessful){
                                    navController.navigate("login_screen")
                                }
                            }
                    }catch (e: Exception){ }

        }

        fun settingFragmentUserSignOut(navController: NavController){
                    Firebase.auth.signOut()
                    navController.navigate("login_screen")

                }


        fun settingFragmentChangeEmail(navController: NavController,newEmail: String){
                    try {
                        val user = Firebase.auth.currentUser
                        user!!.updateEmail(newEmail)
                            .addOnCompleteListener {
                                if (it.isSuccessful){
                                    navController.navigate("setting_screen")
                                }
                            }
                    }catch (e: Exception){ }
        }



}