package com.bitcodetech.findroomies.postdetails.viewmodels

import androidx.lifecycle.ViewModel
import com.bitcodetech.findroomies.postdetails.repository.PostDetailsRepository

class PostDetailsViewModel(
    private  val postDetailsRepository: PostDetailsRepository
) : ViewModel() {
}