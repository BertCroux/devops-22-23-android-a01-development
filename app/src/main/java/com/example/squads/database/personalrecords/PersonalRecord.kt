package com.example.squads.database.personalrecords

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDate

@Entity(tableName = "personal_record")
data class PersonalRecord(
    @PrimaryKey
    val id: Int,
    val userId: Int,
    val exerciseId: Int,
    val amountOfReps: Int,
    val weightUsed: Double,
    val achievedOn: LocalDate
)
