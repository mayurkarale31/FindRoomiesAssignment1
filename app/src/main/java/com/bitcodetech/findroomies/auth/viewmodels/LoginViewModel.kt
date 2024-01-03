package com.bitcodetech.findroomies.auth.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bitcodetech.findroomies.auth.models.Credentials
import com.bitcodetech.findroomies.auth.repository.LoginRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel (
    private val loginRepository : LoginRepository
): ViewModel() {

    val loginUpdateAvailableLiveData =MutableLiveData<Boolean>()

    fun validateCredentials(
        username : String,
        password : String
    ) {
            CoroutineScope(Dispatchers.IO).launch {
                val response = loginRepository.validateCredentials (
                    Credentials(username, password)
                )

                withContext(Dispatchers.Main) {
                    loginUpdateAvailableLiveData.postValue(response)
                }
            }
    }
}