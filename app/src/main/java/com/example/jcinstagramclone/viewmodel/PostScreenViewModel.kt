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
class PostScreenViewModel @Inject constructor(private var repository: DatabaseDaoRepository) : ViewModel() {

    var allPostList = MutableLiveData<List<Post>>()
    init {
        readAllPost()
        allPostList = repository.getAllPost()
    }

    fun readAllPost(){
        viewModelScope.launch {
            repository.readDataAllPost()
        }
    }

}