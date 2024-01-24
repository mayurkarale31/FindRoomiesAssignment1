package com.bitcodetech.findroomies.myprofile.models

import java.io.Serializable

data class UserProfile(
    val userProfileImage : String,
    val userName : String,
    val contactNo : Int,
    val whatsappNo : Int,
    val dob : String
): Serializable
