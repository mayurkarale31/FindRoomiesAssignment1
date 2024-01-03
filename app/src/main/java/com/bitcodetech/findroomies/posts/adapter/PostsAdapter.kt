package com.bitcodetech.findroomies.posts.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bitcodetech.findroomies.R
import com.bitcodetech.findroomies.databinding.PostsViewBinding
import com.bitcodetech.findroomies.posts.models.Post

class PostsAdapter(
    private val posts : ArrayList<Post>
): RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {

    inner class PostsViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val postsViewBinding : PostsViewBinding
        init {
            postsViewBinding = PostsViewBinding.bind(view)
        }
    }

    override fun getItemCount() = posts.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        return PostsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.posts_view, null)
        )
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        //holder.postsViewBinding.post = posts[position]

        holder.postsViewBinding.roomimg.setImageResource(posts[position].roomimage)
        holder.postsViewBinding.txtOwnername.text = posts[position].name
        holder.postsViewBinding.txtAddress.text = posts[position].address
        holder.postsViewBinding.txtRent.text = posts[position].rent.toString()
    }
}