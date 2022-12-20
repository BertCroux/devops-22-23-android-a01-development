package com.example.squads.repository.accounts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.squads.database.SquadsRoomDatabase
import com.example.squads.database.accounts.asDomain
import com.example.squads.domain.accounts.Account
import com.example.squads.network.accounts.AccountApi
import com.example.squads.network.accounts.Reservation
import com.example.squads.network.accounts.asDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AccountRepository(private val database: SquadsRoomDatabase) {
    val account: LiveData<Account> = Transformations.map(database.accountDao.getAccountDetails(1)) {
        //Log.d("AccountRepository", it.toString());
        it?.asDomain()
    }

    suspend fun refreshAccount() {
        withContext(Dispatchers.IO) {
            try {
                val account = AccountApi.retrofitService.getUserDetailsAsync(1).await()
                database.accountDao.insert(account.asDatabase())
                Log.e("AccountRepository", account.toString())
            }catch(e: Exception) {
                Log.e("AccountRepository", e.message.toString())
            }
        }
    }



    suspend fun reserveSession(reservation: Reservation) {
        withContext(Dispatchers.IO) {
            try {
                val reservation = AccountApi.retrofitService.ReserveSession(reservation).await()

                // saving in room database of Reservations
                Log.e("AccountRepository", account.toString())
            }catch(e: Exception) {
                Log.e("AccountRepository", e.message.toString())
            }
        }
    }
}