package com.example.jcinstagramclone.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.jcinstagramclone.data.repo.DatabaseDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UploadViewModel @Inject constructor(private var repository: DatabaseDaoRepository) : ViewModel() {

    fun uploadPost(selectedPicture: Uri, description: String,  navController: NavController,isTrue:Boolean){
        viewModelScope.launch {
            repository.uploadPost(selectedPicture, description = description, navController, isTrue)
        }
    }
}