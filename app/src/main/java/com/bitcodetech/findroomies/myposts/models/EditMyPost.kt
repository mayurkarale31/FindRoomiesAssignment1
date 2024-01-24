package com.bitcodetech.findroomies.myposts.models

import java.io.Serializable

data class EditMyPost(
    val address: String,
    val state: String,
    val country: String,
    val pincode: String,
    val latitude: String,
    val longitude: String,
    val deposite: String,
    val rent: String,
    val availableFrom: String,
    val noOfCurrentRoommates: String,
    val noOfCurrentFemaleRoommates: String,
    val noOfCurrentMaleRoommates: String,
    val isFurnished: String,
    val minAge: String,
    val maxAge: String,
    val genderPreference: String,
    val occupation: String,
    val noOfRoommatesRequired: String,
    val myPostImage: String
) : Serializable
