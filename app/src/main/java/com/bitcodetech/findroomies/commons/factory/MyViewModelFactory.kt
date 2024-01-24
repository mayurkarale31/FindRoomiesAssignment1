package com.bitcodetech.findroomies.commons.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bitcodetech.findroomies.addposts.repository.AddPostRepository
import com.bitcodetech.findroomies.addposts.viewmodels.AddPostViewModel
import com.bitcodetech.findroomies.auth.repository.LoginRepository
import com.bitcodetech.findroomies.auth.viewmodels.LoginViewModel
import com.bitcodetech.findroomies.commons.repository.Repository
import com.bitcodetech.findroomies.myposts.repository.MyPostRepository
import com.bitcodetech.findroomies.myposts.viewmodels.MyPostViewModel
import com.bitcodetech.findroomies.ownerdetails.repository.OwnerDetailsRepository
import com.bitcodetech.findroomies.ownerdetails.viewmodels.OwnerDetailsViewModel
import com.bitcodetech.findroomies.postdetails.repository.PostDetailsRepository
import com.bitcodetech.findroomies.postdetails.viewmodels.PostDetailsViewModel
import com.bitcodetech.findroomies.posts.repository.PostsRepository
import com.bitcodetech.findroomies.posts.viewmodels.PostsViewModel
import com.bitcodetech.findroomies.register.repository.RegistrationRepository
import com.bitcodetech.findroomies.register.viewmodels.RegistrationViewModel
import java.lang.Exception

class MyViewModelFactory(
        private val repository: Repository
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(LoginViewModel::class.java) && repository is LoginRepository) {
            return LoginViewModel(repository) as T
        }
        if(modelClass.isAssignableFrom(PostsViewModel::class.java) && repository is PostsRepository) {
            return PostsViewModel(repository) as T
        }
        if(modelClass.isAssignableFrom(AddPostViewModel::class.java) && repository is AddPostRepository) {
            return AddPostViewModel(repository) as T
        }
        if(modelClass.isAssignableFrom(PostDetailsViewModel::class.java) && repository is PostDetailsRepository) {
            return PostDetailsViewModel(repository) as T
        }
        if(modelClass.isAssignableFrom(OwnerDetailsViewModel::class.java) && repository is OwnerDetailsRepository) {
            return OwnerDetailsViewModel(repository) as T
        }
        if(modelClass.isAssignableFrom(RegistrationViewModel::class.java) && repository is RegistrationRepository) {
            return RegistrationViewModel(repository) as T
        }
        if(modelClass.isAssignableFrom(MyPostViewModel::class.java) && repository is MyPostRepository) {
            return MyPostViewModel(repository) as T
        }
        throw Exception("Unable to create view model...")
    }
}