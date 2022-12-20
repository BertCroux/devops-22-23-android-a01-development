package com.example.squads.screens.sessions

import kotlinx.datetime.LocalDateTime

data class Session(val startDate: LocalDateTime, val endDate: LocalDateTime,
                   val typeOfSession: String, val trainer: String, val spotsLeft: Number)