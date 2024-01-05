package com.bitcodetech.findroomies.posts.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bitcodetech.findroomies.R
import com.bitcodetech.findroomies.addposts.fragments.AddPostFragment
import com.bitcodetech.findroomies.databinding.PostsFragmentBinding
import com.bitcodetech.findroomies.commons.factory.MyViewModelFactory
import com.bitcodetech.findroomies.posts.adapter.PostsAdapter
import com.bitcodetech.findroomies.posts.repository.PostsRepository
import com.bitcodetech.findroomies.posts.viewmodels.PostsViewModel

class PostsFragment : Fragment() {

    private lateinit var binding : PostsFragmentBinding

    private lateinit var postsViewModel : PostsViewModel
    private lateinit var postsAdapter : PostsAdapter
    private var addPostsFragment = AddPostFragment()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PostsFragmentBinding.inflate(layoutInflater)

        initViews()
        initViewModels()
        initAdapter()
        initObservers()
        initListeners()

        postsViewModel.fetchPosts()

        return binding.root
    }

    private fun initListeners() {
        binding.recyclerPosts.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        postsViewModel.fetchPosts()
                    }
                }
            })
        binding.btnAddPosts.setOnClickListener {
                addAddPostsFragment()
        }
    }

    private fun addAddPostsFragment(){
        parentFragmentManager.beginTransaction()
            .add(R.id.mainContainer, addPostsFragment, null)
            .addToBackStack(null)
            .commit()
    }

    private fun initObservers() {
        postsViewModel.postsUpdateAvailableLiveData.observe(
            viewLifecycleOwner
        ) {
            if(it) {
                postsAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun initAdapter() {
        postsAdapter = PostsAdapter(postsViewModel.posts)
        binding.recyclerPosts.adapter = postsAdapter
    }

    private fun initViewModels() {
        postsViewModel = ViewModelProvider(
            this,
            MyViewModelFactory(
                PostsRepository()
            )
        )[PostsViewModel::class.java]
    }

    private fun initViews() {
        binding.recyclerPosts.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL,
            false)
    }
}