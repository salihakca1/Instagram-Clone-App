package com.example.jcinstagramclone.viewmodel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.jcinstagramclone.data.entity.Post
import com.example.jcinstagramclone.data.entity.Story
import com.example.jcinstagramclone.data.repo.DatabaseDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddStoryViewModel @Inject constructor(private var repository: DatabaseDaoRepository) : ViewModel() {

    var storyList = MutableLiveData<List<Story>>()
    init {
        readStory()
        storyList = repository.getStories()
    }

    fun readStory(){
        repository.readDataStories()
    }

    fun uploadStory(selectedPicture: Uri, navController: NavController, isTrue:Boolean){
        repository.uploadStory(selectedPicture, navController, isTrue)
    }

}