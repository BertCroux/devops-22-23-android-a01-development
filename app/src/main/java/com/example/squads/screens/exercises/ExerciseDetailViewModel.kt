package com.example.squads.screens.exercises

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.squads.screens.exercises.models.PersonalRecord
import kotlinx.datetime.LocalDate

class ExerciseDetailViewModel : ViewModel() {

    // list of all personalrecords
    private val _pRecords = MutableLiveData<List<PersonalRecord>>()

    init {
        getPersonalRecords()
    }

    fun getPersonalRecords() {
        _pRecords.value = listOf(
            PersonalRecord(1, 1, 1, 1, 20.0, LocalDate(2022, 10, 19)),
            PersonalRecord(2, 1, 1, 5, 21.0, LocalDate(2022, 10, 18)),
            PersonalRecord(3, 1, 1, 10, 22.0, LocalDate(2022, 10, 17)),
            PersonalRecord(4, 1, 2, 1, 23.0, LocalDate(2022, 10, 16)),
            PersonalRecord(5, 1, 2, 5, 24.0, LocalDate(2022, 10, 15)),
            PersonalRecord(6, 1, 2, 10, 25.0, LocalDate(2022, 10, 14)),
            PersonalRecord(7, 1, 3, 1, 26.0, LocalDate(2022, 10, 13)),
            PersonalRecord(8, 1, 3, 5, 27.0, LocalDate(2022, 10, 12)),
            PersonalRecord(9, 1, 3, 10, 28.0, LocalDate(2022, 10, 11)),
            PersonalRecord(10, 1, 4, 1, 29.0, LocalDate(2022, 10, 10)),
            PersonalRecord(11, 1, 4, 5, 30.0, LocalDate(2022, 10, 11)),
            PersonalRecord(12, 1, 5, 1, 21.0, LocalDate(2022, 10, 12)),
            PersonalRecord(13, 1, 5, 10, 22.0, LocalDate(2022, 10, 13)),
            PersonalRecord(14, 1, 6, 1, 23.0, LocalDate(2022, 10, 14)),
            PersonalRecord(15, 1, 6, 10, 24.0, LocalDate(2022, 10, 15)),
            PersonalRecord(16, 1, 7, 1, 25.0, LocalDate(2022, 10, 16)),
            PersonalRecord(17, 1, 7, 5, 26.0, LocalDate(2022, 10, 17)),
        )
    }

    fun getRecordsOfTypeAndUser(type: Int, user: Int): List<PersonalRecord> {
        return _pRecords.value?.filter { pr -> pr.userId == user && pr.exerciseId == type } ?: emptyList()
    }
}
