package com.example.squads.database.accounts

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.squads.domain.accounts.Account
import com.example.squads.domain.accounts.Address

@Entity(tableName = "account")
data class DatabaseAccount (
        @PrimaryKey
        val userId: Int,
        val firstName: String,
        val lastName: String,
        val email: String,
        val phoneNumber: String,
        val lengthInCm: Int,
        val physicalIssues: String,
        val drugsUsed: String,
        val addressLine1: String,
        val addressLine2: String,
        val zipCode: String,
        val city: String
)

fun DatabaseAccount.asDomain(): Account {
        return Account(
                userId = userId,
                firstName = firstName,
                lastName = lastName,
                email = email,
                phoneNumber = phoneNumber,
                address = Address(
                        addressLine1 = addressLine1,
                        addressLine2 = addressLine2,
                        zipCode = zipCode,
                        city = city
                ),
                lengthInCm = lengthInCm,
                physicalIssues = physicalIssues,
                drugsUsed = drugsUsed
        )
}