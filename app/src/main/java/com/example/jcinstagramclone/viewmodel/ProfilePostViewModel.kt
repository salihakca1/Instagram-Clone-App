package com.example.jcinstagramclone.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jcinstagramclone.data.entity.Post
import com.example.jcinstagramclone.data.repo.DatabaseDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfilePostViewModel @Inject constructor(private val repository: DatabaseDaoRepository): ViewModel() {
    var profilePostList = MutableLiveData<List<Post>>()

    init {
        readProfilePost()
        profilePostList = repository.getProfilePost()
    }

    fun readProfilePost(){
        viewModelScope.launch {
            repository.readDataProfilePost()
        }
    }
}