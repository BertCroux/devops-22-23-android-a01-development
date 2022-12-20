package com.example.squads.database.exercises

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise")
data class Exercise(
    @PrimaryKey
    val id: Int,
    val name: String,
    val explanation: String,
    val imageUrl: String
)