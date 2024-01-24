package com.bitcodetech.findroomies.ownerdetails.repository

import com.bitcodetech.findroomies.commons.repository.Repository
import com.bitcodetech.findroomies.ownerdetails.models.OwnerDetails

class OwnerDetailsRepository : Repository() {
    fun fetchOwnerDetails(
        ownerDetails: OwnerDetails
    ) : Boolean{
        return true
    }
}