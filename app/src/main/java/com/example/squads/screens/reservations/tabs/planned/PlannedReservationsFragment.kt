package com.example.squads.screens.reservations.tabs.planned

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.squads.R
import com.example.squads.databinding.FragmentPlannedReservationsBinding
import com.example.squads.screens.reservations.ReservationViewModel

class PlannedReservationsFragment : Fragment() {
    private val sharedViewModel: ReservationViewModel by activityViewModels()
    private lateinit var binding: FragmentPlannedReservationsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_planned_reservations, container, false
        )



        Log.d("PRF", sharedViewModel.plannedReservation.value.toString())

        //binding.lifecycleOwner = this

        binding.containerViewPlannedReservations.layoutManager = LinearLayoutManager(activity)
        binding.containerViewPlannedReservations.adapter = PlannedReservationAdaptor(sharedViewModel.plannedReservation)



        return binding.root
    }
}
