package com.bitcodetech.findroomies.register.models

data class UserRegistration(
    val username : String,
    val email : String,
    val password : String,
    val confirmPassword  : String,
    val gender  : String,
    val mobileNo : Int,
    val whatsappNo  : Int,
    val date_of_birth  :String
)
