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
import com.bitcodetech.findroomies.databinding.AddImageFragmentBinding
import com.bitcodetech.findroomies.posts.fragments.PostsFragment

class AddImageFragment : Fragment() {
    private lateinit var binding : AddImageFragmentBinding
    private lateinit var addPostViewModel: AddPostViewModel

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
        binding.btnAddPost.setOnClickListener {
            val postsFragment = PostsFragment()

            parentFragmentManager.beginTransaction()
                .add(R.id.mainContainer,postsFragment,null)
                .addToBackStack(null)
                .commit()
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