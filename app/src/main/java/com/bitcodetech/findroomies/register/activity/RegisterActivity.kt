package com.bitcodetech.findroomies.register.activity

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bitcodetech.findroomies.R
import com.bitcodetech.findroomies.auth.activity.Login_Activity
import com.bitcodetech.findroomies.commons.factory.MyViewModelFactory
import com.bitcodetech.findroomies.databinding.RegisterActivityBinding
import com.bitcodetech.findroomies.register.repository.RegistrationRepository
import com.bitcodetech.findroomies.register.viewmodels.RegistrationViewModel

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: RegisterActivityBinding
    private lateinit var registrationViewModel: RegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()
        initListeners()
        initModels()
        initObserver()

        val gender = resources.getStringArray(R.array.Gender)

        val spinner = binding.genderSpinner
        if (spinner != null) {
            val adapter =  ArrayAdapter(this@RegisterActivity, android.R.layout.simple_spinner_item, gender)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedGender = gender[position]
                    binding.edtGender.text = selectedGender
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    Toast.makeText(this@RegisterActivity, "Please select gender", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
    private fun initListeners(){
        binding.btnRegister.setOnClickListener {
            registrationViewModel.postUserRegistration(
                binding.edtName.text.toString(),
                binding.edtEmailId.text.toString(),
                binding.edtPassword.text.toString(),
                binding.edtConfirmPassword.text.toString(),
                binding.edtGender.text.toString(),
                binding.edtMobileNo.text.toString().toInt(),
                binding.edtWhatsappNo.text.toString().toInt(),
                binding.edtDob.text.toString()
            )

        }
        binding.imgCalender.setOnClickListener(
            View.OnClickListener {
                val datePickerDialog = DatePickerDialog(
                    this,
                    MyOnDateSetListener(),
                    2023,
                    1,
                    24
                )
                datePickerDialog.show()
            }
        )
    }

    inner class MyOnDateSetListener : DatePickerDialog.OnDateSetListener {
        override fun onDateSet(datePicker: DatePicker, year: Int, month: Int, dayOfMonth: Int) {
            binding.edtDob.setText(dayOfMonth.toString() + " - " + (month + 1) + " - " + year)
        }
    }

    private fun startMainActivity() {
        startActivity(
            Intent(this, Login_Activity::class.java)
        )
    }

    private fun initObserver() {
        registrationViewModel.registrationMutableLiveData.observe(
            this
        ) {
            if (it) {
                finish()
                startMainActivity()
            }
        }
    }

    private fun initModels() {
        registrationViewModel = ViewModelProvider(
            this,
            MyViewModelFactory(
                RegistrationRepository()
            )
        )[RegistrationViewModel::class.java]
    }
    private fun initViews(){
        binding = RegisterActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}