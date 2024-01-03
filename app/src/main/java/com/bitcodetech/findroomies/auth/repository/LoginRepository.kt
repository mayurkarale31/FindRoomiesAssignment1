package com.bitcodetech.findroomies.auth.repository

import com.bitcodetech.findroomies.auth.models.Credentials
import com.bitcodetech.findroomies.commons.repository.Repository

class LoginRepository() : Repository() {

    fun validateCredentials(
        credentials: Credentials
    ) : Boolean{
        return true
    }
}