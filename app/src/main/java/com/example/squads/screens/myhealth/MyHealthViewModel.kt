package com.example.squads.screens.myhealth

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.squads.database.SquadsRoomDatabase
import com.example.squads.database.measurements.asDomain
import com.example.squads.repository.measurements.MeasurementRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MyHealthViewModel(application: Application) : AndroidViewModel(application) {
    // list of all the users measurements
    private val database = SquadsRoomDatabase.getInstance(application.applicationContext)
    private val repository = MeasurementRepository(database)

    /*val measurements = Transformations.map(repository.allMeasurements.asLiveData()) {
        it.asDomain()
    }*/
    val measurements = repository.measurements

    val latestMeasurement = Transformations.map(repository.latest.asLiveData()) {
        it.asDomain()
    }

    // variable so the graphsfragment knows what to display
    private val _typeDataGraph = MutableLiveData<String?>()
    val typeDataGraph: LiveData<String?>
        get() = _typeDataGraph

    init {
        viewModelScope.launch {
            repository.refreshMeasurements()
        }
    }

    // navigation to graphs-----------------------------------------------------------------
    private val _navigateToGraphs = MutableLiveData<Boolean?>()
    val navigateToGraphs: LiveData<Boolean?>
        get() = _navigateToGraphs

    fun doneNavigatingToGraphs() {
        _navigateToGraphs.value = null
    }

    fun onNavigateToGraphs(type: String) {
        // set type so that the graphsfragment can know what to display
        _typeDataGraph.value = type

        // set the navigate to true so that the myhealth fragment knows when to navigate
        _navigateToGraphs.value = true
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MyHealthViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MyHealthViewModel(app) as T
            }

            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}