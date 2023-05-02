package com.example.jcinstagramclone.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.jcinstagramclone.data.repo.AuthDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(private val repository: AuthDaoRepository): ViewModel() {

    fun signUpButton(navController: NavController,nameAndSurname: String, userName: String, email: String, password: String) {
        viewModelScope.launch {
            repository.signUpClick(
                navController = navController,
                email = email,
                password = password,
                userName = userName,
                name = nameAndSurname
            )
        }
    }

    fun logInButton(navController: NavController, email: String, password: String){
        viewModelScope.launch {
            repository.logInClick(navController, email, password)
        }
    }

    fun resetEmail(navController: NavController, email: String){
        viewModelScope.launch {
            repository.sendPasswordResetEmail(navController, email)
        }
    }

    fun getCurrentUser(navController: NavController){
        repository.getCurrentUser(navController)
    }

    fun changePassword(navController: NavController, newPassword: String){
        repository.settingFragmentChangePassword(navController, newPassword)
    }

    fun deleteUser(navController: NavController){
        viewModelScope.launch {
            repository.settingFragmentUserDelete(navController)
        }
    }

    fun signOut(navController: NavController){
        viewModelScope.launch {
            repository.settingFragmentUserSignOut(navController)
        }
    }

    fun updateEmail(navController: NavController, newEmail: String){
        viewModelScope.launch {
            repository.settingFragmentChangeEmail(navController, newEmail)
        }
    }
}