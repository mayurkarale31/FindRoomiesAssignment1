package com.bitcodetech.findroomies.auth.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bitcodetech.findroomies.MainActivity
import com.bitcodetech.findroomies.commons.factory.MyViewModelFactory
import com.bitcodetech.findroomies.auth.repository.LoginRepository
import com.bitcodetech.findroomies.auth.viewmodels.LoginViewModel
import com.bitcodetech.findroomies.databinding.LoginActivityBinding
import com.bitcodetech.findroomies.register.activity.RegisterActivity

class Login_Activity : AppCompatActivity() {

    private lateinit var binding : LoginActivityBinding

    private lateinit var loginViewModel: LoginViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initViewModels()
        initObservers()
        initListeners()
    }

    private fun initViewModels(){
        loginViewModel = ViewModelProvider(
            this,
            MyViewModelFactory(
                LoginRepository(
                )
            )
        )[LoginViewModel::class.java]
    }

    private fun initObservers(){
        loginViewModel.loginUpdateAvailableLiveData.observe(
            this,
        ){
            if (it){
                finish()
                startMainActivity()
            }
            else{

            }
        }
    }

    private fun startMainActivity(){
        startActivity(
            Intent(
                this@Login_Activity, MainActivity::class.java
            )
        )
    }

    private fun initListeners(){
        binding.btnLogin.setOnClickListener {
            loginViewModel.validateCredentials(
            binding.edtUserName.text.toString(),
            binding.edtPassword.text.toString()
            )
        }
        binding.txtRegister.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}