package com.bitcodetech.findroomies.addposts.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bitcodetech.findroomies.R
import com.bitcodetech.findroomies.addposts.repository.AddPostRepository
import com.bitcodetech.findroomies.addposts.viewmodels.AddPostViewModel
import com.bitcodetech.findroomies.commons.factory.MyViewModelFactory
import com.bitcodetech.findroomies.databinding.AddImageFragmentBinding
import com.bitcodetech.findroomies.posts.fragments.PostsFragment
import com.bumptech.glide.Glide

class AddImageFragment : Fragment() {
    private lateinit var binding : AddImageFragmentBinding
    private lateinit var addPostViewModel: AddPostViewModel
    private val PICK_IMAGE_REQUEST = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AddImageFragmentBinding.inflate(layoutInflater)

        initViewModels()
        initObserver()
        initListener()
        return binding.root
    }

    private fun initListener(){
        binding.listingsImage1.setOnClickListener {
            openImagePicker()
        }

        binding.listingsImage2.setOnClickListener {
            openImagePicker()
        }

        binding.listingsImage3.setOnClickListener {
            openImagePicker()
        }
        binding.btnAddPost.setOnClickListener {
            val postsFragment = PostsFragment()

            parentFragmentManager.beginTransaction()
                .add(R.id.mainContainer,postsFragment,null)
                .addToBackStack(null)
                .commit()
        }
    }

    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImage: Uri? = data.data
            // Load the selected image into the appropriate ImageView using Glide
            when {
                binding.listingsImage1.tag == null -> {
                    binding.listingsImage1.tag = selectedImage
                    Glide.with(this)
                        .load(selectedImage)
                        .into(binding.listingsImage1)
                }
                binding.listingsImage2.tag == null -> {
                    binding.listingsImage2.tag = selectedImage
                    Glide.with(this)
                        .load(selectedImage)
                        .into(binding.listingsImage2)
                }
                binding.listingsImage3.tag == null -> {
                    binding.listingsImage3.tag = selectedImage
                    Glide.with(this)
                        .load(selectedImage)
                        .into(binding.listingsImage3)
                }
                else -> {
                    // Handle the case where all ImageViews are already populated
                }
            }
        }
    }
    private fun initObserver() {
        addPostViewModel.addPostMutableLiveData.observe(
            viewLifecycleOwner
        ) {
            parentFragmentManager.popBackStack()
        }
    }
    private fun initViewModels(){
        addPostViewModel = ViewModelProvider(
            this,
            MyViewModelFactory(
                AddPostRepository()
            )
        )[AddPostViewModel::class.java]
    }
}