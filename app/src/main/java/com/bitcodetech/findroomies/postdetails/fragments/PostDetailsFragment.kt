package com.bitcodetech.findroomies.postdetails.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.bitcodetech.findroomies.R
import com.bitcodetech.findroomies.databinding.DetailsFragmentBinding
import com.bitcodetech.findroomies.ownerdetails.fragments.OwnerDetailsFragment
import com.bitcodetech.findroomies.posts.fragments.PostsFragment
import com.google.android.material.bottomsheet.BottomSheetDialog

class PostDetailsFragment : Fragment() {
    private lateinit var binding: DetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailsFragmentBinding.inflate(layoutInflater)

        initListener()
        return binding.root
    }

    private fun initListener(){
        binding.btnContact.setOnClickListener {
            /*val ownerDetailsFragment = OwnerDetailsFragment()

            parentFragmentManager.beginTransaction()
                .add(R.id.mainContainer,ownerDetailsFragment,null)
                .addToBackStack(null)
                .commit()*/

            val dialog = BottomSheetDialog(requireContext())
            val bottomSheetView = layoutInflater.inflate(R.layout.owner_details_fragment, null)
            val btnDone = bottomSheetView.findViewById<Button>(R.id.btnDone)

            btnDone.setOnClickListener {
                dialog.dismiss()
                val postsFragment = PostsFragment()

                parentFragmentManager.beginTransaction()
                    .add(R.id.mainContainer,postsFragment,null)
                    .addToBackStack(null)
                    .commit()
            }

            dialog.setCancelable(false)
            dialog.setContentView(bottomSheetView)
            dialog.show()
        }
    }
}