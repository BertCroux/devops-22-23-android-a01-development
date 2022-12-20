package com.example.squads.screens.exercises.models

import kotlinx.datetime.LocalDate

data class PersonalRecord(
    val id: Int,
    val userId: Int,
    val exerciseId: Int,
    val amountOfReps: Int,
    val weightUsed: Double,
    val achievedOn: LocalDate
)
