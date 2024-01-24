package com.bitcodetech.findroomies.myposts.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bitcodetech.findroomies.R
import com.bitcodetech.findroomies.databinding.MyPostViewBinding
import com.bitcodetech.findroomies.myposts.models.MyPost

class MyPostAdapter(
    private val myPost: ArrayList<MyPost>
): RecyclerView.Adapter<MyPostAdapter.MyPostViewHolder>() {

    interface OnMyPostClickListener{
        fun onMyPostListener(editMyPostModel: MyPost, position: Int, myPostAdapter: MyPostAdapter)
    }
    var onMyPostClickListener : OnMyPostClickListener? = null

    inner class MyPostViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val binding  : MyPostViewBinding

        init {
            binding = MyPostViewBinding.bind(view)
            binding.root.setOnClickListener {
                onMyPostClickListener?.onMyPostListener(
                    myPost[adapterPosition],
                    adapterPosition,
                    this@MyPostAdapter
                )
            }
        }
    }

    override fun getItemCount() = myPost.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.my_post_view, null)
        return MyPostViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyPostViewHolder, position: Int) {
        val post = myPost[position]

        holder.binding.imgPosts.setImageResource(post.imageUrl)
        holder.binding.txtName.text = post.name
        holder.binding.txtAddress.text = post.address
        holder.binding.txtRent.text = post.rent.toString()
    }
}