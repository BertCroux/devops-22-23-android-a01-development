package com.example.squads.domain.accounts

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
)
