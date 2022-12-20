package com.example.squads.domain.sessions

import java.util.*

data class Session(
    val SessionId : Int,
    val StartDate : Date,
    val EndDate : Date,
    val SessionType : String,
    val Instructor : String,
    val CanCancel : Boolean,
    val CanSignUp : Boolean,
    val AmoutOfReservations : Int,


    )
