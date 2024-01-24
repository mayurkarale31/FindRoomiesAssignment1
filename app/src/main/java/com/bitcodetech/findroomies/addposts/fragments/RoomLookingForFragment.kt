package com.bitcodetech.findroomies.addposts.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bitcodetech.findroomies.R
import com.bitcodetech.findroomies.addposts.models.AddPostModel
import com.bitcodetech.findroomies.databinding.RoomLookingForFragmentBinding

class RoomLookingForFragment : Fragment() {
    private lateinit var binding : RoomLookingForFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RoomLookingForFragmentBinding.inflate(layoutInflater)

        initListener()
        initSpinner()
        binding.root.setOnClickListener {  }
        return binding.root
    }

    private fun initSpinner(){
        val gender = resources.getStringArray(R.array.Gender)

        val spinner = binding.genderSpinner
        if (spinner != null) {
            val adapter =  ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, gender)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedGender = gender[position]
                    binding.edtGenderPreference.text = selectedGender
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    Toast.makeText(context, "Please select gender", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun initListener(){
        binding.btnNext.setOnClickListener {
            val minAge = binding.edtMinAge.text.toString()
            val maxAge = binding.edtMaxAge.text.toString()
            val genderPreference = binding.edtGenderPreference.text.toString()
            val occupation = binding.edtOccupation.text.toString()
            val noOfRoommatesRequired = binding.edtNoOfRoommatesRequired.text.toString()

            val addPostModel  : AddPostModel = arguments?.getSerializable("addPostModel")as?AddPostModel?: AddPostModel(
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "","",
                "",
                "",
                "",
                "",
                minAge,
                maxAge,
                genderPreference,
                occupation,
                noOfRoommatesRequired,
                ""

            )
            val bundle = Bundle()
            bundle.putSerializable("addPostModel",addPostModel)

            val addImageFragment = AddImageFragment()
            addImageFragment.arguments = bundle

            parentFragmentManager.beginTransaction()
                .add(R.id.mainContainer,addImageFragment,null)
                .addToBackStack(null)
                .commit()
        }
    }
}