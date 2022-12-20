package com.example.squads.screens.exercises

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.squads.screens.exercises.models.ExerciseType

class ExercisesPagerAdapter(fa: Fragment, val viewModel: ExercisesViewModel) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = viewModel.exercises.value?.size ?: 0

    override fun createFragment(position: Int): Fragment {
        var et: ExerciseType? = viewModel.exercises.value?.get(position)
        Log.d("Adapter", viewModel.exercises.toString())

        return ExerciseDetailFragment(et ?: ExerciseType(1, "No exercise", "No exercise", ""))
    }
}
// package com.example.squads.screens.exercises3
//
// import androidx.fragment.app.Fragment
// import androidx.viewpager2.adapter.FragmentStateAdapter
//
// class ExercisesPagerAdapter(fa: Fragment, val vm: ExercisesViewModel): FragmentStateAdapter(fa) {
//    override fun getItemCount(): Int = vm.exercises.value?.size ?: 0
//    val personalRecordsViewModel: PersonalRecordsViewModel = PersonalRecordsViewModel()
//
//
//    override fun createFragment(position: Int): Fragment {
//        var et: ExerciseType? = vm.exercises.value?.get(position)
//        return ExerciseDetailFragment(et ?: ExerciseType(1, "No exercise", "No exercise", ""),
//            personalRecordsViewModel.getRecordsOfTypeAndUser(et?.id ?: -1, 1) ?: emptyList()
//        )
//    }
// }
