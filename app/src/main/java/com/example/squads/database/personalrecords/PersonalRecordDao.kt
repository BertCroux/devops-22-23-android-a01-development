package com.example.squads.database.personalrecords

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonalRecordDao {
    @Query("SELECT * FROM personal_record")
    fun getAllPersonalRecords(): Flow<List<PersonalRecord>>

    @Query("SELECT * FROM personal_record WHERE userId = :userId AND exerciseId = :exerciseId")
    fun getPersonalRecordsByUserIdAndExerciseId(userId: Int, exerciseId: Int): Flow<List<PersonalRecord>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(personalRecord: PersonalRecord)
}