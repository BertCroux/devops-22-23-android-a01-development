package com.example.squads.screens.account

data class Account (
        val userId: Int,
        val firstName: String,
        val lastName: String,
        val email: String,
        val phoneNumber: String,
        val address: Address,
        val lengthInCm: Int,
        val physicalIssues: String,
        val drugsUsed: String,
        val password: String,
)

data class Address(
    val street: String,
    val number: String,
    //hier string want bvb 1A
    val city: String,
    val zipCode: Int,
)



