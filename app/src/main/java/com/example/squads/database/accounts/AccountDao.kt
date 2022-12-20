package com.example.squads.database.accounts

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AccountDao {
    @Query("SELECT * FROM account WHERE userId = :userId")
    fun getAccountDetails(userId: Int): LiveData<DatabaseAccount>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(account: DatabaseAccount)
}