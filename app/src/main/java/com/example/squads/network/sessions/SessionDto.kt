package com.example.squads.network.sessions

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.squads.database.sessions.Session
import com.squareup.moshi.Json
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

data class SessionDtoContainer(
    @Json(name = "sessions")
    val replyBody: List<SessionDto>
)

data class SessionDto (
    @Json(name = "sessionId")
    val sessionId : Int,
    @Json(name = "startDate")
    val startDate : String,
    @Json(name = "endDate")
    val endDate : String,
    @Json(name = "amountOfReservations")
    val amountOfReservations : Int,
    @Json(name = "sessionType")
    val sessionType : String,
    @Json(name = "instructor")
    val instructor : String,

    @Json(name = "canCancel")
    val canCancel : Boolean,
    @Json(name = "canSignUp")
    val canSignUp : Boolean,

    @Json(name = "canJoinWaitList")
    val canJoinWaitlist: Boolean,
)

@RequiresApi(Build.VERSION_CODES.O)
fun SessionDtoContainer.asDomain(): List<Session> {
    return replyBody.map {
        Session(
            sessionId = it.sessionId,
            startDate = Date.from(Instant.parse(it.startDate)),
            endDate = Date.from(Instant.parse(it.endDate)),
            SessionType = it.sessionType,
            Instructor = it.instructor,
            spotsLeft = it.amountOfReservations,

            canCancel = it.canCancel,
            canSignUp = it.canSignUp,
            canJoinWaitlist = it.canJoinWaitlist,
        )
    }
}

/**
 * "2022-11-01T00:00:00" fromat from dates
 */
@RequiresApi(Build.VERSION_CODES.O)
fun List<SessionDto>.asDatabase(): Array<Session> {
    val x = this.map {
        Session(
            sessionId = it.sessionId,
            startDate = Date.from(LocalDateTime.parse(it.startDate).atZone(ZoneId.systemDefault()).toInstant()),
            endDate = Date.from(LocalDateTime.parse(it.endDate).atZone(ZoneId.systemDefault()).toInstant()),
            SessionType = it.sessionType,
            Instructor = it.instructor,
            spotsLeft = it.amountOfReservations,

            canCancel = it.canCancel,
            canSignUp = it.canSignUp,
            canJoinWaitlist = it.canJoinWaitlist,

        )
    }.toTypedArray()

    //temporary logging
    x.iterator().forEach {
        Log.d("MeasurementDto", it.toString())
    }

    return x
}

