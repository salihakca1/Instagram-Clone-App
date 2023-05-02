package com.example.jcinstagramclone.data.repo

import android.annotation.SuppressLint
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.example.jcinstagramclone.data.entity.Post
import com.example.jcinstagramclone.data.entity.Story
import com.example.jcinstagramclone.data.entity.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID
import javax.inject.Inject

class DatabaseDaoRepository @Inject constructor(var auth: FirebaseAuth, var fireStore: FirebaseFirestore, var storage: FirebaseStorage)  {

         var profilePostList = MutableLiveData<List<Post>>()
         var userProfileDetailList = MutableLiveData<List<User>>()
         var userStoriesList = MutableLiveData<List<Story>>()
         var allPostList = MutableLiveData<List<Post>>()
         var storyDetailPhotoList = MutableLiveData<List<Story>>()
         var postDetailList = MutableLiveData<List<Post>>()

         init {
            postDetailList = MutableLiveData()
            storyDetailPhotoList = MutableLiveData()
            allPostList = MutableLiveData()
            userStoriesList = MutableLiveData()
            userProfileDetailList = MutableLiveData()
            profilePostList = MutableLiveData()

        }

        fun getPostDetail(): MutableLiveData<List<Post>>{
            return postDetailList
        }

        fun getStoryDetailPhoto(): MutableLiveData<List<Story>>{
            return storyDetailPhotoList
        }

        fun getAllPost(): MutableLiveData<List<Post>>{
            return allPostList
        }

        fun getStories(): MutableLiveData<List<Story>>{
            return userStoriesList
        }

        fun getProfileDetail(): MutableLiveData<List<User>>{
            return userProfileDetailList
        }

        fun getProfilePost(): MutableLiveData<List<Post>>{
            return profilePostList
        }

      fun readUserProfileDetail(){
        try {
            fireStore.collection("users")
                .whereEqualTo("userId", auth.currentUser!!.uid)
                .addSnapshotListener{value, error ->
                    val list = ArrayList<User>()
                    if (error != null){

                    }else{
                        if (value != null){
                            if (!value.isEmpty){
                                val documents = value.documents
                                for (document in documents){
                                    val userId = document.get("userId") as String
                                    val name = document.get("name") as String
                                    val userName = document.get("userName") as String
                                    val email = document.get("email") as String
                                    val password = document.get("password") as String
                                    val downloadUrl = document.get("imageUrl") as String
                                    val bio = document.get("bio") as String
                                    val user = User(userId, name, userName, email, password, downloadUrl, bio)
                                    list.add(user)
                                    userProfileDetailList.value = list
                                }
                            }
                        }
                    }
                }
        }catch (e: Exception){}
    }

        fun updateUserProfileDetail(navController: NavController, selectedPicture: Uri, name: String, userName: String, bio: String){
            val uuid = UUID.randomUUID()
            val imageName = "$uuid.jpg"

            val reference = storage.reference
            val imageReference = reference.child("profileImages").child(imageName)
            if (selectedPicture != null) {
                imageReference.putFile(selectedPicture).addOnSuccessListener {
                    val uploadPictureReference =
                        storage.reference.child("profileImages").child(imageName)
                    uploadPictureReference.downloadUrl.addOnSuccessListener {
                        val downloadUrl = it.toString()
                        if (auth.currentUser != null) {

                            val postMap = hashMapOf<String,Any>()
                            postMap.put("name", name)
                            postMap.put("userName", userName)
                            postMap.put("bio", bio)
                            postMap.put("imageUrl", downloadUrl)

                            fireStore.collection("users")
                                .document(auth.currentUser!!.uid)
                                .update(postMap)
                                .addOnSuccessListener {
                                    val user = Firebase.auth.currentUser
                                    val profileUpdates =  userProfileChangeRequest {
                                        displayName = userName
                                        photoUri = Uri.parse(downloadUrl)
                                    }
                                    user!!.updateProfile(profileUpdates)
                                        .addOnCompleteListener{
                                            navController.navigate("main_screen")
                                        }
                                }

                        }
                    }
                }
            }
        }

        @SuppressLint("SuspiciousIndentation")
        fun uploadPost(selectedImage: Uri, description: String, navController: NavController, isTrue: Boolean){
            try {
                val uuid = UUID.randomUUID()
                val imageName = "$uuid.jpg"

                val reference = storage.reference
                val imageReference = reference.child("images").child(imageName)

                if (isTrue){
                    if (selectedImage != null){
                        imageReference.putFile(selectedImage).addOnSuccessListener {
                            val uploadPictureReference = storage.reference.child("images").child(imageName)
                            uploadPictureReference.downloadUrl.addOnSuccessListener {
                                val downloadUrl = it.toString()
                                if (auth.currentUser != null){
                                    val postMap = hashMapOf<String, Any>()
                                    val documentId = fireStore.collection("posts").document().id
                                    postMap.put("documentId", documentId)
                                    postMap.put("userId", auth.currentUser!!.uid)
                                    postMap.put("userImage", auth.currentUser!!.photoUrl.toString())
                                    postMap.put("date", com.google.firebase.Timestamp.now())
                                    postMap.put("userName", auth.currentUser!!.displayName!!)
                                    postMap.put("downloadUrl", downloadUrl)
                                    postMap.put("description",description)

                                    fireStore.collection("posts")
                                        .document(documentId)
                                        .set(postMap)
                                        .addOnSuccessListener {
                                            navController.navigate("main_screen")
                                        }
                                }
                            }
                        }
                    }
                }
            }catch (e: Exception){}

        }

        fun readDataProfilePost(){
            try {
                fireStore.collection("posts")
                    .whereEqualTo("userId", auth.currentUser!!.uid)
                    .orderBy("date", Query.Direction.DESCENDING)
                    .addSnapshotListener{value, error ->
                        val list = ArrayList<Post>()
                        if (error != null){

                        }else{
                            if (value != null){
                                if (!value.isEmpty){
                                    val documents = value.documents
                                    for (document in documents){
                                        val documentId = document.get("documentId") as String
                                        val userId = document.get("userId") as String
                                        val userImage = document.get("userImage") as String
                                        val userName = document.get("userName") as String
                                        val description = document.get("description") as String
                                        val downloadUrl = document.get("downloadUrl") as String
                                        val post = Post(documentId,userId,userImage,userName,downloadUrl,description)
                                        list.add(post)
                                        profilePostList.value = list
                                    }
                                }
                            }
                        }
                    }
            }catch (e: Exception){}
        }


    fun readDataProfilePostDetail(postId: String){
        try {
            fireStore.collection("posts")
                .whereEqualTo("documentId", postId)
                .addSnapshotListener{value, error ->
                    val list = ArrayList<Post>()
                    if (error != null){

                    }else{
                        if (value != null){
                            if (!value.isEmpty){
                                val documents = value.documents
                                for (document in documents){
                                    val documentId = document.get("documentId") as String
                                    val userId = document.get("userId") as String
                                    val userImage = document.get("userImage") as String
                                    val userName = document.get("userName") as String
                                    val description = document.get("description") as String
                                    val downloadUrl = document.get("downloadUrl") as String
                                    val post = Post(documentId,userId,userImage,userName,downloadUrl,description)
                                    list.add(post)
                                    postDetailList.value = list
                                }
                            }
                        }
                    }
                }
        }catch (e: Exception){}
    }
    fun readDataAllPost(){
        try {
            fireStore.collection("posts")
                .orderBy("date", Query.Direction.DESCENDING)
                .addSnapshotListener{value, error ->
                    val list = ArrayList<Post>()
                    if (error != null){

                    }else{
                        if (value != null){
                            if (!value.isEmpty){
                                val documents = value.documents
                                for (document in documents){
                                    val documentId = document.get("documentId") as String
                                    val userId = document.get("userId") as String
                                    val userImage = document.get("userImage") as String
                                    val userName = document.get("userName") as String
                                    val description = document.get("description") as String
                                    val downloadUrl = document.get("downloadUrl") as String
                                    val post = Post(documentId,userId,userImage,userName,downloadUrl,description)
                                    list.add(post)
                                    allPostList.value = list
                                }
                            }
                        }
                    }
                }
        }catch (e: Exception){}
    }



    @SuppressLint("SuspiciousIndentation")
    fun uploadStory(selectedImage: Uri, navController: NavController, isTrue: Boolean){
        try {
            val uuid = UUID.randomUUID()
            val imageName = "$uuid.jpg"

            val reference = storage.reference
            val imageReference = reference.child("images").child(imageName)

            if (isTrue){
                if (selectedImage != null){
                    imageReference.putFile(selectedImage).addOnSuccessListener {
                        val uploadPictureReference = storage.reference.child("images").child(imageName)
                        uploadPictureReference.downloadUrl.addOnSuccessListener {
                            val downloadUrl = it.toString()
                            if (auth.currentUser != null){
                                val postMap = hashMapOf<String, Any>()
                                val documentId = fireStore.collection("stories").document().id
                                postMap.put("documentId",documentId)
                                postMap.put("userId", auth.currentUser!!.uid)
                                postMap.put("userImage", auth.currentUser!!.photoUrl!!)
                                postMap.put("downloadUrl", downloadUrl)
                                postMap.put("userName", auth.currentUser!!.displayName!!)
                                postMap.put("date", com.google.firebase.Timestamp.now())

                                fireStore.collection("stories")
                                    .document(documentId)
                                    .set(postMap)
                                    .addOnSuccessListener {
                                        navController.navigate("main_screen")
                                    }
                            }
                        }
                    }
                }
            }
        }catch (e: Exception){}

    }

    fun readDataStories(){
        try {
            fireStore.collection("stories")
                .orderBy("date", Query.Direction.DESCENDING)
                .addSnapshotListener{value, error ->
                    val list = ArrayList<Story>()
                    if (error != null){

                    }else{
                        if (value != null){
                            if (!value.isEmpty){
                                val documents = value.documents
                                for (document in documents){
                                    val documentId = document.get("documentId") as String
                                    val userId = document.get("userId") as String
                                    val userImage = document.get("userImage") as String
                                    val userName = document.get("userName") as String
                                    val downloadUrl = document.get("downloadUrl") as String
                                    val story = Story(documentId, userId, userImage, userName, downloadUrl)
                                    list.add(story)
                                    userStoriesList.value = list
                                }
                            }
                        }
                    }
                }
        }catch (e: Exception){}
    }

    fun readDataStoryDetailPhoto(storyId: String){
        try {
            fireStore.collection("stories")
                .whereEqualTo("documentId", storyId)
                .addSnapshotListener{value, error ->
                    val list = ArrayList<Story>()
                    if (error != null){

                    }else{
                        if (value != null){
                            if (!value.isEmpty){
                                val documents = value.documents
                                for (document in documents){
                                    val documentId = document.get("documentId") as String
                                    val userId = document.get("userId") as String
                                    val userImage = document.get("userImage") as String
                                    val userName = document.get("userName") as String
                                    val downloadUrl = document.get("downloadUrl") as String
                                    val story = Story(documentId, userId, userImage, userName, downloadUrl)
                                    list.add(story)
                                    storyDetailPhotoList.value = list
                                }
                            }
                        }
                    }
                }
        }catch (e: Exception){}
    }


}

