package com.example.squads.repository.measurements

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.asLiveData
import com.example.squads.database.SquadsRoomDatabase
import com.example.squads.database.accounts.asDomain
import com.example.squads.database.measurements.asDomain
import com.example.squads.domain.measurements.Measurement
import com.example.squads.network.measurements.MeasurementApi
import com.example.squads.network.measurements.asDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MeasurementRepository(private val database: SquadsRoomDatabase) {

    val allMeasurements = database.measurementDao.getAllMeasurements()
    val latest = database.measurementDao.getLatestMeasurement()


    val measurements = MediatorLiveData<List<Measurement>>()

    private var changeableLiveData = Transformations.map(database.measurementDao.getAllMeasurements().asLiveData()) {
        it.asDomain()
    }


    init {
        measurements.addSource(changeableLiveData){
            measurements.value = it
        }
    }

    suspend fun refreshMeasurements() {
        withContext(Dispatchers.IO) {
            try {
                val measurements = MeasurementApi.retrofitService.getAllMeasurementsFromUserAsync(1).await()
                //database.measurementDao.insertAll(*measurements.asDatabase())
                database.measurementDao.insert(measurements.asDatabase()[0])
                //again logging purpuses

            }catch(e: Exception) {
                Log.e("MeasurementRepo", e.message.toString())
            }
        }
    }
}