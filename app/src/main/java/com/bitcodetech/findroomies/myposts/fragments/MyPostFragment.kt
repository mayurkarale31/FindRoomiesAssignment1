package com.bitcodetech.findroomies.myposts.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bitcodetech.findroomies.R
import com.bitcodetech.findroomies.commons.factory.MyViewModelFactory
import com.bitcodetech.findroomies.databinding.MyPostFragmentBinding
import com.bitcodetech.findroomies.myposts.adapter.MyPostAdapter
import com.bitcodetech.findroomies.myposts.models.MyPost
import com.bitcodetech.findroomies.myposts.repository.MyPostRepository
import com.bitcodetech.findroomies.myposts.viewmodels.MyPostViewModel

class MyPostFragment : Fragment() {
    private lateinit var binding : MyPostFragmentBinding
    private lateinit var myPostViewModel : MyPostViewModel
    private lateinit var myPostAdapter : MyPostAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MyPostFragmentBinding.inflate(layoutInflater)

        initViews()
        initViewModels()
        initAdapter()
        initObserver()
        initListeners()

        myPostViewModel.fetchPosts()
        setHasOptionsMenu(true)
        binding.root.setOnClickListener {  }
        return binding.root
    }

    private fun initListeners() {
        binding.recyclerMyPost.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        myPostViewModel.fetchPosts()
                    }
                }
            })

        myPostAdapter.onMyPostClickListener =
            object : MyPostAdapter.OnMyPostClickListener {
                override fun onMyPostListener(
                    myPost: MyPost,
                    position: Int,
                    myPostAdapter: MyPostAdapter
                ) {
                    showDetailsFragment(myPost)
                    // Log.e("tag","event deligationn model work")
                }
            }

    }
    private fun showDetailsFragment(myPost: MyPost) {
        val showDetailsFragment = ShowDetailsFragment()

        //val input = Bundle()
        parentFragmentManager.beginTransaction()
            .add(R.id.mainContainer, showDetailsFragment, null)
            .addToBackStack(null)
            .commit()
    }
    private fun initObserver() {
        myPostViewModel.myPostsMutableLiveData.observe(
            viewLifecycleOwner
        ) {
            if (it) {
                myPostAdapter.notifyDataSetChanged()
            }
        }
    }
    private fun initAdapter() {
        myPostAdapter = MyPostAdapter(myPostViewModel.myPost)
        binding.recyclerMyPost.adapter = myPostAdapter
    }
    private fun initViewModels() {
        myPostViewModel = ViewModelProvider(
            this,
            MyViewModelFactory(
                MyPostRepository()
            )
        )[MyPostViewModel::class.java]
    }
    private fun initViews() {
        binding.recyclerMyPost.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }
}