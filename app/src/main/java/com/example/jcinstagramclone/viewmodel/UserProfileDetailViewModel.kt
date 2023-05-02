package com.example.jcinstagramclone.viewmodel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.jcinstagramclone.data.entity.User
import com.example.jcinstagramclone.data.repo.DatabaseDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserProfileDetailViewModel @Inject constructor(private val repository: DatabaseDaoRepository): ViewModel() {
    var profileDetailList = MutableLiveData<List<User>>()

    init {
        readProfileDetail()
        profileDetailList = repository.getProfileDetail()
    }

    fun readProfileDetail(){
        viewModelScope.launch {
            repository.readUserProfileDetail()
        }
    }

    fun updateProfileDetail(navController: NavController, selectedPicture: Uri, name: String, userName: String, bio: String){
        viewModelScope.launch {
            repository.updateUserProfileDetail(navController, selectedPicture, name, userName, bio)
        }
    }
}