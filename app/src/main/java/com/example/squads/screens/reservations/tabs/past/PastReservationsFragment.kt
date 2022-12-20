package com.example.squads.screens.reservations.tabs.past

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.squads.R
import com.example.squads.databinding.FragmentPastReservationsBinding
import com.example.squads.screens.reservations.ReservationViewModel

class PastReservationsFragment : Fragment() {
    private val sharedViewModel: ReservationViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentPastReservationsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_past_reservations, container, false
        )


        binding.pastReservationContainer2.layoutManager = LinearLayoutManager(activity)
        binding.pastReservationContainer2.adapter = PastReservationAdaptor(sharedViewModel.pastReservation)

        return binding.root
    }
}
