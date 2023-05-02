package com.example.jcinstagramclone.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jcinstagramclone.data.entity.Story
import com.example.jcinstagramclone.data.repo.DatabaseDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StoryDetailViewModel @Inject constructor(private var repository: DatabaseDaoRepository) : ViewModel() {

    var storyDetailList = MutableLiveData<List<Story>>()

    init {
        storyDetailList = repository.getStoryDetailPhoto()
    }

    fun readStoryDetail(storyId: String){
        repository.readDataStoryDetailPhoto(storyId)
    }

}