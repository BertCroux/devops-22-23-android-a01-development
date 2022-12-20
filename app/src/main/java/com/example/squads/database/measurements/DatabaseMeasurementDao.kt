package com.example.squads.database.measurements

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
//TODO from flow to livedata?

@Dao
interface DatabaseMeasurementDao {
    @Query("SELECT * FROM measurement")
    fun getAllMeasurements(): Flow<List<DatabaseMeasurement>>

    @Query("SELECT * FROM measurement ORDER BY measuredOn DESC LIMIT 1")
    fun getLatestMeasurement(): Flow<DatabaseMeasurement>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(measurement: DatabaseMeasurement)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg measurements: DatabaseMeasurement)
}