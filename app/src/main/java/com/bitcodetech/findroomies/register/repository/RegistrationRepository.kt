package com.bitcodetech.findroomies.register.repository

import com.bitcodetech.findroomies.commons.repository.Repository
import com.bitcodetech.findroomies.register.models.UserRegistration

class RegistrationRepository : Repository() {
    fun postUserRegistration(
        userRegistration: UserRegistration
    ):Boolean{
        return true
    }
}