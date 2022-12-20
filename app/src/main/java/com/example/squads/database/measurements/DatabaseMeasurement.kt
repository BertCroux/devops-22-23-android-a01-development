package com.example.squads.database.measurements

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.squads.domain.measurements.Measurement
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

@Entity(tableName = "measurement")
data class DatabaseMeasurement(
    @PrimaryKey
    val id: Int,
    val weight: Double,
    val fatPercentage: Double,
    val musclePercentage: Double,
    val waistCircumference: Double,
    val measuredOn: Date,
)

@RequiresApi(Build.VERSION_CODES.O)
fun List<DatabaseMeasurement>.asDomain(): List<Measurement> {
    return map {
        Measurement(
            id = it.id,
            weight = it.weight,
            fatPercentage = it.fatPercentage,
            musclePercentage = it.musclePercentage,
            waistCircumference = it.waistCircumference,
            measuredOn = it.measuredOn)

    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun DatabaseMeasurement.asDomain(): Measurement {
    return Measurement(
        id = id,
        weight = weight,
        fatPercentage = fatPercentage,
        musclePercentage = musclePercentage,
        waistCircumference = waistCircumference,
        measuredOn = measuredOn
    )
}