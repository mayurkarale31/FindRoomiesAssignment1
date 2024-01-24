package com.bitcodetech.findroomies.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bitcodetech.findroomies.R
import com.bitcodetech.findroomies.databinding.AboutProjectFragmentBinding
import com.bitcodetech.findroomies.posts.fragments.PostsFragment

class AboutProjectFragment : Fragment() {
    private lateinit var binding: AboutProjectFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AboutProjectFragmentBinding.inflate(layoutInflater)

        initListener()
        binding.root.setOnClickListener{}
        return binding.root
    }

    private fun initListener() {
        binding.btnDone.setOnClickListener {
            val postsFragment = PostsFragment()

            parentFragmentManager.beginTransaction()
                .add(R.id.mainContainer, postsFragment, null)
                .addToBackStack(null)
                .commit()
        }
    }
}