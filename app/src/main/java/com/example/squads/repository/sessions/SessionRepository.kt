package com.example.squads.repository.sessions

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.squads.database.SquadsRoomDatabase
import com.example.squads.network.sessions.SessionApi
import com.example.squads.network.sessions.asDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SessionRepository(private val database: SquadsRoomDatabase) {
    val allSessions = database.sessionDto.GetWeekOverView()

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun refreshSessions() {
        withContext(Dispatchers.IO) {
            try {
                val thisWeekSessions = SessionApi.retrofitService.getCurrentWeekSessionOverview(1).await()

                val nextWeekSessions = SessionApi.retrofitService.getNextWeekSessionOverview(1).await()

                val thisWeekSessionsList = thisWeekSessions.replyBody
                val nextWeeksessionsList = nextWeekSessions.replyBody

                //val checkSession = SessionApi.retrofitService.
                database.sessionDto.insert(thisWeekSessionsList.asDatabase())
                database.sessionDto.insert(nextWeeksessionsList.asDatabase())

            }catch(e: Exception) {
                Log.e("SessionRepository", e.message.toString())
            }
        }
    }
}