package com.example.squads.network.accounts

import android.accounts.Account
import android.util.Log
import com.example.squads.database.accounts.DatabaseAccount
import com.example.squads.database.accounts.Address
import com.squareup.moshi.Json

data class AccountDto (
    @Json(name = "id")
    var userId: Int,
    var firstName: String,
    var lastName: String,
    var email: String,
    var phoneNumber: String,
    var address: AddressDto,
    @Json(name = "length")
    var lengthInCm: Int,
    var physicalIssues: String,
    var drugsUsed: String,
)

data class AddressDto (
    var addressLine1: String,
    var addressLine2: String,
    var zipCode: String,
    var city: String
)

fun AccountDto.asDatabase(): DatabaseAccount {
    Log.d("AccountDto", this.toString())
    return DatabaseAccount(
        userId = userId,
        firstName = firstName,
        lastName = lastName,
        email = email,
        phoneNumber = phoneNumber,
        addressLine1 = address.addressLine1,
        addressLine2 = address.addressLine2,
        zipCode = address.zipCode,
        city = address.city,
        lengthInCm = lengthInCm,
        physicalIssues = physicalIssues,
        drugsUsed = drugsUsed
    )
}