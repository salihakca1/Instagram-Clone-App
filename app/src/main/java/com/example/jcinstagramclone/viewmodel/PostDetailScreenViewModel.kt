package com.example.jcinstagramclone.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jcinstagramclone.data.entity.Post
import com.example.jcinstagramclone.data.repo.DatabaseDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostDetailScreenViewModel @Inject constructor(private var repository: DatabaseDaoRepository) : ViewModel() {
    var postDetailList = MutableLiveData<List<Post>>()

    init {
        postDetailList = repository.getPostDetail()
    }

    fun readPostDetail(postId: String){
        repository.readDataProfilePostDetail(postId)
    }

}