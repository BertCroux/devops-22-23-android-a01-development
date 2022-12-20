package com.example.squads.database.sessions

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SessionDao {
    @Query("SELECT * FROM session")
    fun GetWeekOverView(): Flow<List<Session>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(session: kotlin.Array<com.example.squads.database.sessions.Session>)
}

