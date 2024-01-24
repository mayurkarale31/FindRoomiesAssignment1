package com.bitcodetech.findroomies.ownerdetails.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bitcodetech.findroomies.ownerdetails.models.OwnerDetails
import com.bitcodetech.findroomies.ownerdetails.repository.OwnerDetailsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OwnerDetailsViewModel(
    private val ownerDetailsRepository: OwnerDetailsRepository
) : ViewModel() {

    val ownerDetailsMutableLiveData = MutableLiveData<Boolean>()

    fun fetchOwnerDetails(
        image : Int,
        name : String,
        whatsppNo : Int,
        contactNo  : Int
    ){
        CoroutineScope(Dispatchers.IO).launch {
            val ownerDetails = ownerDetailsRepository.fetchOwnerDetails(
                OwnerDetails(
                    image,
                    name,
                    whatsppNo,
                    contactNo
                )
            )
            withContext(Dispatchers.Main){
                ownerDetailsMutableLiveData.postValue(true)
            }
        }
    }
}