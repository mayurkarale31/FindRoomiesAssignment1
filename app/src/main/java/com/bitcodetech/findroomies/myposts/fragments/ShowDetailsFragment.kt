package com.bitcodetech.findroomies.myposts.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bitcodetech.findroomies.R
import com.bitcodetech.findroomies.databinding.ShowDetailsFragmentBinding
import com.bitcodetech.findroomies.myposts.models.EditMyPost

class ShowDetailsFragment : Fragment() {
    private lateinit var binding : ShowDetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ShowDetailsFragmentBinding.inflate(layoutInflater)

        initListener()
        setHasOptionsMenu(true)

        binding.root.setOnClickListener {  }
        return binding.root
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.my_post_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.editMyPost -> {

                val bundles = Bundle()

                val editMyPost = EditMyPost(
                    "address",
                    "state",
                    "country",
                    "pincode",
                    "latitude",
                    "longitude",
                    "deposite",
                    "rent",
                    "availableFrom",
                    "noOfCurrentRoommates",
                    "noOfCurrentFemaleRoommates",
                    "noOfCurrentMaleRoommates",
                    "isFurnished",
                    "minAge",
                    "maxAge",
                    "genderPreference",
                    "occupation",
                    "noOfRoommatesRequired",
                    "postImage"
                )
                bundles.putSerializable("editMyPost",editMyPost)

                val editMyPostFragment = EditMyPostFragment()
                parentFragmentManager.beginTransaction()
                    .add(R.id.mainContainer, editMyPostFragment, null)
                    .addToBackStack(null)
                    .commit()

                return true
            }
            R.id.deleteMyPost -> {

                val builder = AlertDialog.Builder(requireContext())
                builder.setMessage("Are you sure you want to delete this post?")
                    .setCancelable(false)
                    .setPositiveButton("Yes") { dialog, id ->
                        // Call a method to delete the post
                        deletePost()
                    }
                    .setNegativeButton("No") { dialog, id ->
                        dialog.dismiss()
                    }
                val alert = builder.create()
                alert.show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun deletePost(){
        Toast.makeText(context,"Post Deleted", Toast.LENGTH_SHORT).show()
        val myPostFragment = MyPostFragment()
        parentFragmentManager.beginTransaction()
            .replace(R.id.mainContainer, myPostFragment)
            .addToBackStack(null)
            .commit()
    }
    private fun initListener(){
        binding.btnDone.setOnClickListener {

            val myPostFragment = MyPostFragment()
            parentFragmentManager.beginTransaction()
                .add(R.id.mainContainer, myPostFragment, null)
                .addToBackStack(null)
                .commit()
        }
    }
}