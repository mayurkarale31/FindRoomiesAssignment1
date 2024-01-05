package com.bitcodetech.findroomies.addposts.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bitcodetech.findroomies.addposts.models.AddPost
import com.bitcodetech.findroomies.addposts.repository.AddPostRepository
import com.bitcodetech.findroomies.posts.models.Post
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddPostViewModel(
    private val addPostRepository : AddPostRepository
) : ViewModel() {

    val addPostUpdateAvailableLiveData = MutableLiveData<Boolean>()

    fun addPost(
        image: Int,
        name: String,
        address: String,
        rent: Int
    ){
        CoroutineScope(Dispatchers.IO).launch {
            val response = addPostRepository.addPost(Post(image, name, address, rent))

            withContext(Dispatchers.Main){
                addPostUpdateAvailableLiveData.postValue(response)
            }
        }
    }




}