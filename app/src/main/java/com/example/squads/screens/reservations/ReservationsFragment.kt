package com.example.squads.screens.reservations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.squads.R
import com.example.squads.databinding.FragmentReservationsBinding
import com.example.squads.screens.reservations.tabs.ReservationsPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ReservationsFragment : Fragment() {
    lateinit var viewModel: ReservationViewModel
    lateinit var binding: FragmentReservationsBinding
    /**
     * Setup tablayout when the reservationsFragment is created
     * TabLayoutMediator is ued to link a tablayout with a ViewPager2
     * @see https://developer.android.com/reference/com/google/android/material/tabs/TabLayoutMediator
     *
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val tabLayout: TabLayout = view.findViewById(R.id.tab_layout)
        val viewPager: ViewPager2 = view.findViewById(R.id.view_pager)
        viewPager.adapter = ReservationsPagerAdapter(this)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.past_res_text)
                else -> {
                    tab.text = getString(R.string.planned_res_text)
                }
            }
        }.attach()
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
        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_reservations, container, false)

        NavigateToSession()

        return binding.root;

    }

    fun NavigateToSession() {
        binding.bookBtn2.setOnClickListener {
            findNavController().navigate(R.id.session)
        }
    }
}
