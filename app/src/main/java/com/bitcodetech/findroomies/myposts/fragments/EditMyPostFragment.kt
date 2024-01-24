package com.bitcodetech.findroomies.myposts.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bitcodetech.findroomies.R
import com.bitcodetech.findroomies.databinding.EditMyPostsFragmentBinding
import com.bitcodetech.findroomies.myposts.models.EditMyPost

class EditMyPostFragment : Fragment() {
    private lateinit var binding: EditMyPostsFragmentBinding
    private var myPost : EditMyPost? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = EditMyPostsFragmentBinding.inflate(layoutInflater)

        editMyPost()
        initListener()
        initSpinners()
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
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

    private fun editMyPost(){
        if(arguments != null){
            myPost = requireArguments().getSerializable("editMyPost") as EditMyPost

            binding.edtAddress.setText(myPost?.address.toString() ?: "")
            binding.edtState.setText(myPost?.state.toString() ?: "")
            binding.edtCountry.setText(myPost?.country.toString() ?: "")
            binding.edtPincode.setText(myPost?.pincode.toString() ?: "")
            binding.edtLatitude.setText(myPost?.latitude.toString() ?: "")
            binding.edtLongitude.setText(myPost?.longitude.toString() ?: "")
            binding.edtDeposite.setText(myPost?.deposite.toString() ?: "")
            binding.edtRent.setText(myPost?.rent.toString() ?: "")
            binding.edtAvailableFrom.setText(myPost?.availableFrom.toString() ?: "")
            binding.edtNoOfCurrentRoommates.setText(myPost?.noOfCurrentRoommates.toString() ?: "")
            binding.edtNoOfCurrentFemaleRoommates.setText(myPost?.noOfCurrentFemaleRoommates.toString() ?: "")
            binding.edtNoOfCurrentMaleRoommates.setText(myPost?.noOfCurrentMaleRoommates.toString() ?: "")
            binding.edtIsFurnished.setText(myPost?.isFurnished.toString() ?: "")
        }

    }
    private fun initListener() {
        binding.btnNext.setOnClickListener {
            val address = binding.edtAddress.text.toString()
            val state = binding.edtState.text.toString()
            val country = binding.edtCountry.text.toString()
            val pincode = binding.edtPincode.text.toString()
            val latitude = binding.edtLatitude.text.toString()
            val longitude = binding.edtLongitude.text.toString()
            val deposit = binding.edtDeposite.text.toString()
            val rent = binding.edtRent.text.toString()
            val availableFrom = binding.edtAvailableFrom.text.toString()
            val noOfCurrentRoommates= binding.edtNoOfCurrentRoommates.text.toString()
            val noOfCurrentFemaleRoommates = binding.edtNoOfCurrentFemaleRoommates.text.toString()
            val noOfCurrentMaleRoommates = binding.edtNoOfCurrentMaleRoommates.text.toString()
            val isFurnished = binding.edtIsFurnished.text.toString()

            val editMyPost = EditMyPost(
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
            bundle.putSerializable("addPostModel", editMyPost)

            val editRoomLookingForFragment = EditRoomLookingForFragment()

            parentFragmentManager.beginTransaction()
                .add(R.id.mainContainer, editRoomLookingForFragment, null)
                .addToBackStack(null)
                .commit()
        }
    }
}