package com.bitcodetech.findroomies.addposts.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bitcodetech.findroomies.addposts.models.AddPostModel
import com.bitcodetech.findroomies.addposts.repository.AddPostRepository
import com.bitcodetech.findroomies.posts.models.Post
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddPostViewModel(
    private val addPostRepository : AddPostRepository
) : ViewModel() {

    val addPostMutableLiveData = MutableLiveData<Boolean>()
    val addPostModels = ArrayList<AddPostModel>()

    fun addPost(
        address: String,
        state: String,
        country: String,
        pincode: String,
        latitude: String,
        longitude: String,
        deposite: String,
        rent: String,
        availableFrom: String,
        noOfCurrentRoommates: String,
        noOfCurrentFemaleRoommates: String,
        noOfCurrentMaleRoommates: String,
        isFurnished: String,
        minAge: String,
        maxAge: String,
        genderPreference: String,
        occupation: String,
        noOfRoommatesRequired: String,
        postImage: String
    ){
        CoroutineScope(Dispatchers.IO).launch {
            val response = addPostRepository.addPost(AddPostModel(
                address,
                state,
                country,
                pincode,
                latitude,
                longitude,
                deposite,
                rent,
                availableFrom,
                noOfCurrentRoommates,
                noOfCurrentFemaleRoommates,
                noOfCurrentMaleRoommates,
                isFurnished,
                minAge,
                maxAge,genderPreference,
                occupation,
                noOfRoommatesRequired,
                postImage
            )
            )
            withContext(Dispatchers.Main){
                addPostMutableLiveData.postValue(response)
            }
        }
    }
}