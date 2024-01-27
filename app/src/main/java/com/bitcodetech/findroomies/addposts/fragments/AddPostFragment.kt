package com.bitcodetech.findroomies.addposts.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bitcodetech.findroomies.R
import com.bitcodetech.findroomies.addposts.models.AddPostModel
import com.bitcodetech.findroomies.addposts.repository.AddPostRepository
import com.bitcodetech.findroomies.addposts.viewmodels.AddPostViewModel
import com.bitcodetech.findroomies.commons.factory.MyViewModelFactory
import com.bitcodetech.findroomies.databinding.AddPostsFragmentBinding
import com.bitcodetech.findroomies.posts.models.Post

class AddPostFragment : Fragment() {

    private lateinit var binding : AddPostsFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AddPostsFragmentBinding.inflate(layoutInflater)

        initViewListeners()
        initSpinners()
        initListeners()

        return binding.root
    }

    private fun initSpinners(){
        val isFurnished = resources.getStringArray(R.array.Is_Furnished)

        val spinner = binding.isFurnishedSpinner
        if (spinner != null) {
            val adapter =  ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, isFurnished)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val isfurnished = isFurnished[position]
                    binding.edtIsFurnished.text = isfurnished
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    Toast.makeText(context, "Please select one option", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initViewListeners(){

        binding.btnNext.setOnClickListener {
            val address =  binding.edtAddress.text.toString()
            val state =  binding.edtState.text.toString()
            val country = binding.edtCountry.text.toString()
            val pincode =  binding.edtPincode.text.toString()
            val latitude = binding.edtLatitude.text.toString()
            val longitude = binding.edtLongitude.text.toString()
            val deposit = binding.edtDeposite.text.toString()
            val rent = binding.edtRent.text.toString()
            val availableFrom = binding.edtAvailableFrom.text.toString()
            val noOfCurrentRoommates = binding.edtNoOfCurrentRoommates.text.toString()
            val noOfCurrentFemaleRoommates = binding.edtNoOfCurrentFemaleRoommates.text.toString()
            val noOfCurrentMaleRoommates = binding.edtNoOfCurrentMaleRoommates.text.toString()
            val isFurnished=  binding.edtIsFurnished.text.toString()

            val addPostModel = AddPostModel(
                address,
                state,
                country,
                pincode,
                latitude,
                longitude,
                deposit,
                rent,
                availableFrom,
                noOfCurrentRoommates,
                noOfCurrentFemaleRoommates,
                noOfCurrentMaleRoommates,
                isFurnished,
                "",
                "",
                "",
                "",
                "",
                ""
            )

            val bundle = Bundle()
            bundle.putSerializable("addPostModel",addPostModel)

            val roomLookingForFragment = RoomLookingForFragment()
            roomLookingForFragment.arguments = bundle

            parentFragmentManager.beginTransaction()
                .add(R.id.mainContainer,roomLookingForFragment,null)
                .addToBackStack(null)
                .commit()
        }
    }

    private fun initListeners(){
        binding.imgOpenMap.setOnClickListener {
            val intent = Intent(requireContext(), MapsActivity::class.java)
            startActivityForResult(intent, MAP_REQUEST_CODE)
        }
    }

    companion object {
        const val MAP_REQUEST_CODE = 123
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == MAP_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val latitude = data?.getDoubleExtra("latitude", 0.0)
            val longitude = data?.getDoubleExtra("longitude", 0.0)
            // Update your TextViews with the latitude and longitude
            // For example:
            binding.edtLatitude.text = "$latitude"
            binding.edtLongitude.text = "$longitude"
        }
    }
}