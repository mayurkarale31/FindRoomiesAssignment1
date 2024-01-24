package com.bitcodetech.findroomies.ownerdetails.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bitcodetech.findroomies.R
import com.bitcodetech.findroomies.commons.factory.MyViewModelFactory
import com.bitcodetech.findroomies.databinding.OwnerDetailsFragmentBinding
import com.bitcodetech.findroomies.ownerdetails.repository.OwnerDetailsRepository
import com.bitcodetech.findroomies.ownerdetails.viewmodels.OwnerDetailsViewModel
import com.bitcodetech.findroomies.posts.fragments.PostsFragment

class OwnerDetailsFragment : Fragment() {
    private lateinit var binding : OwnerDetailsFragmentBinding
    private lateinit var ownerDetailsViewModel: OwnerDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = OwnerDetailsFragmentBinding.inflate(layoutInflater)

        initViewModel()
        initObserver()
        initListeners()

        return binding.root
    }
    private fun initListeners(){
        binding.btnDone.setOnClickListener {
            var postsFragment = PostsFragment()

            parentFragmentManager.beginTransaction()
                .add(R.id.mainContainer,postsFragment,null)
                .addToBackStack(null)
                .commit()

        }
    }
    private fun initObserver(){
        ownerDetailsViewModel.ownerDetailsMutableLiveData.observe(
            viewLifecycleOwner
        ){
            if (it){
                parentFragmentManager.popBackStack()
            }
        }
    }
    private fun initViewModel(){
        ownerDetailsViewModel = ViewModelProvider(
            this,
            MyViewModelFactory(
                OwnerDetailsRepository()
            )
        )[OwnerDetailsViewModel::class.java]
    }
}