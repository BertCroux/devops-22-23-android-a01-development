package com.example.squads.network.measurements

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.squads.database.measurements.DatabaseMeasurement
import com.example.squads.domain.measurements.Measurement
import com.squareup.moshi.Json
import kotlinx.datetime.LocalDate
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

data class MeasurementDtoContainer(
    @Json(name = "body")
    val replyBody: List<MeasurementDto>
)

data class MeasurementDto (
    @Json(name = "id")
    val id: Int,
    @Json(name = "weight")
    val weight: Double,
    @Json(name = "fatPercentage")
    val fatPercentage: Double,
    @Json(name = "musclePercentage")
    val musclePercentage: Double,
    @Json(name = "waistCircumference")
    val waistCircumfercence: Double,
    @Json(name = "measurementDate")
    val measuredOn: String,
    //@Json(name = "bmi")
    //val bmi: Double
)

@RequiresApi(Build.VERSION_CODES.O)
fun MeasurementDtoContainer.asDomain(): List<Measurement> {
    return replyBody.map {
        Measurement(
            id = it.id,
            weight = it.weight,
            fatPercentage = it.fatPercentage,
            musclePercentage = it.musclePercentage,
            waistCircumference = it.waistCircumfercence,
            measuredOn = Date.from(Instant.parse(it.measuredOn))
        )
    }
}

/**
 * "2022-11-01T00:00:00" fromat from dates
 */
@RequiresApi(Build.VERSION_CODES.O)
fun List<MeasurementDto>.asDatabase(): Array<DatabaseMeasurement> {
    val x = this.map {
        DatabaseMeasurement(
            id = it.id,
            weight = it.weight,
            fatPercentage = it.fatPercentage,
            musclePercentage = it.musclePercentage,
            waistCircumference = it.waistCircumfercence,
            measuredOn = Date.from(LocalDateTime.parse(it.measuredOn).atZone(ZoneId.systemDefault()).toInstant())
        )
    }.toTypedArray()

    //temporary logging
    x.iterator().forEach {
        Log.d("MeasurementDto", it.toString())
    }

    return x
}
