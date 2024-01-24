package com.bitcodetech.findroomies.addposts.repository

import com.bitcodetech.findroomies.addposts.models.AddPostModel
import com.bitcodetech.findroomies.commons.repository.Repository
import com.bitcodetech.findroomies.posts.models.Post

class AddPostRepository : Repository() {

    fun addPost(
        post : AddPostModel
    ) : Boolean{
        return true
    }
}