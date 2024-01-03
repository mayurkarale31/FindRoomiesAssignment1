package com.bitcodetech.findroomies.posts.repository

import com.bitcodetech.findroomies.R
import com.bitcodetech.findroomies.commons.repository.Repository
import com.bitcodetech.findroomies.posts.models.Post

class PostsRepository : Repository() {



    fun fetchPosts(): List<Post>{

        val posts = ArrayList<Post>()

        posts.add(
            Post(
                R.drawable.room1,
                "Mayur",
                "Katraj",
                2000)
        )
        posts.add(
            Post(
                R.drawable.room2,
                "Kajal",
                "Dighi",
                3000)
        )
        posts.add(
            Post(
                R.drawable.room3,
                "Abhi",
                "Karvenagar",
                5000)
        )
        posts.add(
            Post(
                R.drawable.room4,
                "Shivam",
                "Baner",
                5500)
        )
        return posts
    }
}