package com.bitcodetech.findroomies.myprofile.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bitcodetech.findroomies.R
import com.bitcodetech.findroomies.databinding.ProfileFragmentBinding
import com.bitcodetech.findroomies.myprofile.models.UserProfile

class ProfileFragment : Fragment() {
    private lateinit var binding : ProfileFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProfileFragmentBinding.inflate(layoutInflater)
        setHasOptionsMenu(true)
        return binding.root
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater){
        menu.clear()
        inflater.inflate(R.menu.my_profile_menu,menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.editProfile-> {
                val editProfileFragment = EditProfileFragment()

                val bundles = Bundle()

                val userProfile = UserProfile(
                    "user_profile_image_url",
                    "user_name",
                    123456789, // contactNo
                    987654321, // whatsappNo
                    "user_dob"
                )
                bundles.putSerializable("profile", userProfile)

                parentFragmentManager.beginTransaction()
                    .add(R.id.mainContainer, editProfileFragment, null)
                    .addToBackStack(null)
                    .commit()

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}