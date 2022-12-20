package com.example.squads.screens.exercises

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.squads.R
import com.example.squads.databinding.FragmentExercisesBinding
import com.google.android.material.tabs.TabLayoutMediator

class ExercisesFragment : Fragment() {

    private lateinit var viewModel: ExercisesViewModel
    lateinit var binding: FragmentExercisesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_exercises, container, false)

        val vm: ExercisesViewModel by activityViewModels()
        viewModel = vm
        binding.exercisesViewModel = viewModel

        binding.exercisesPager.adapter = ExercisesPagerAdapter(this, viewModel)
        TabLayoutMediator(binding.exercisesTabs, binding.exercisesPager) { tab, position -> tab.text = viewModel.exercises.value?.get(position)?.name }.attach()

        binding.lifecycleOwner = this
        return binding.root
    }
}
