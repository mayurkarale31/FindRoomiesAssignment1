package com.bitcodetech.findroomies.addposts.fragments

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
import com.bitcodetech.findroomies.databinding.AddPostBinding
import com.bitcodetech.findroomies.posts.models.Post

class AddPostFragment : Fragment() {

    private lateinit var binding : AddPostBinding

    private lateinit var addPostViewModel: AddPostViewModel

    private var selectedImage : Int =0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddPostBinding.inflate(layoutInflater)

        initViewModels()
        initObservers()
        initListeners()
        initImageSelectListener()


        return binding.root
    }

    private fun initViewModels(){
        addPostViewModel = ViewModelProvider(
            this,
            MyViewModelFactory(
            AddPostRepository()
            )
        )[AddPostViewModel::class.java]
    }
    private fun initObservers(){
        addPostViewModel.addPostUpdateAvailableLiveData.observe(
            viewLifecycleOwner
        ){
            removeCurrentFragment()
        }
    }

    private fun removeCurrentFragment(){
        parentFragmentManager.popBackStack()
    }

    private fun initListeners(){
        binding.btnAddPost.setOnClickListener {
            addPostViewModel.addPost(
                selectedImage,
                binding.edtName.text.toString(),
                binding.edtAddress.text.toString(),
                binding.edtRent.text.toString().toInt()
            )
        }
    }

    private fun initImageSelectListener(){

        binding.img1.setOnClickListener {
            selectedImage = R.drawable.room1
            binding.img1.alpha = 0.5f
            binding.img2.alpha = 1.0f
            binding.img3.alpha = 1.0f
        }
        binding.img2.setOnClickListener {
            selectedImage = R.drawable.room2
            binding.img2.alpha = 0.5f
            binding.img1.alpha = 1.0f
            binding.img3.alpha = 1.0f
        }
        binding.img3.setOnClickListener {
            selectedImage = R.drawable.room3
            binding.img3.alpha = 0.5f
            binding.img2.alpha = 1.0f
            binding.img1.alpha = 1.0f
        }
    }
}