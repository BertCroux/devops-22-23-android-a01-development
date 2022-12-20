package com.example.squads.database.sessions

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "session")
data class Session(
    @PrimaryKey
    val sessionId: Int,
    val startDate: Date,
    val endDate: Date,
    val SessionType: String,
    val Instructor: String,
    val spotsLeft: Int,


    val canCancel : Boolean,
    val canSignUp : Boolean,
    val canJoinWaitlist  : Boolean,



)

@RequiresApi(Build.VERSION_CODES.O)
fun List<Session>.asDomain(): List<Session> {
    return map {
        Session(
            sessionId = it.sessionId,
            startDate = it.startDate,
            endDate = it.endDate,
            SessionType = it.SessionType,
            Instructor = it.Instructor,
            spotsLeft = it.spotsLeft,

            canCancel = it.canCancel,
            canSignUp = it.canSignUp,
            canJoinWaitlist = it.canJoinWaitlist,

        )

    }
}
