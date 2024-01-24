package com.bitcodetech.findroomies.myposts.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bitcodetech.findroomies.R
import com.bitcodetech.findroomies.databinding.EditRoomLookingForFragmentBinding
import com.bitcodetech.findroomies.myposts.models.EditMyPost

class EditRoomLookingForFragment : Fragment() {
    private lateinit var binding : EditRoomLookingForFragmentBinding
    private var myPost : EditMyPost? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = EditRoomLookingForFragmentBinding.inflate(layoutInflater)

        editRoomDetails()
        initListener()
        initSpinner()
        return binding.root
    }

    private fun initSpinner() {

        val gender = resources.getStringArray(R.array.Gender)
        val spinner = binding.genderSpinner
        if (spinner != null) {
            val adapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, gender)
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

    private fun editRoomDetails(){
        if(arguments != null){
            myPost = requireArguments().getSerializable("editMyPost") as EditMyPost

            binding.edtMinAge.setText(myPost?.minAge.toString() ?: "")
            binding.edtMaxAge.setText(myPost?.maxAge.toString() ?: "")
            binding.edtGenderPreference.setText(myPost?.genderPreference.toString() ?: "")
            binding.edtOccupation.setText(myPost?.occupation.toString() ?: "")
            binding.edtNoOfRoommatesRequired.setText(myPost?.noOfRoommatesRequired.toString() ?: "")
        }

    }

    private fun initListener(){
        binding.btnNext.setOnClickListener {

            val minAge = binding.edtMinAge.text.toString()
            val maxAge = binding.edtMaxAge.text.toString()
            val genderPreference = binding.edtGenderPreference.text.toString()
            val occupation = binding.edtOccupation.text.toString()
            val noOfRoommatesRequired = binding.edtNoOfRoommatesRequired.text.toString()

            val editMyPost  : EditMyPost = arguments?.getSerializable("addPostModel")as? EditMyPost
                ?: EditMyPost(
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
            bundle.putSerializable("addPostModel",editMyPost)

            val editImageFragment = EditImageFragment()
            //addImageFragment.arguments = bundle

            parentFragmentManager.beginTransaction()
                .add(R.id.mainContainer,editImageFragment,null)
                .addToBackStack(null)
                .commit()
        }
    }
}