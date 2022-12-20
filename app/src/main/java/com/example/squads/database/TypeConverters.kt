package com.example.squads.database

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * Class for converting a string to LocalDateTime.
 */
class Converters {
    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
            /**
             * Text 'Tue Nov 01 00:00:00 GMT+01:00 2022' could not be parsed at index 0
             */
    fun fromString(value: String?): Date? {
        val pattern = DateTimeFormatter.ofPattern("EEE LLL dd HH:mm:ss O yyyy")
        val localDateTime = LocalDateTime.parse(value!!, pattern)
        println(localDateTime.toString())
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant())
    }

    @TypeConverter
    fun dateToString(date: Date?): String {
        return date.toString()
    }
}