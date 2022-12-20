package com.example.squads.screens.reservations.tabs

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.squads.screens.reservations.tabs.past.PastReservationsFragment
import com.example.squads.screens.reservations.tabs.planned.PlannedReservationsFragment

/**
 * Implementation for the tabs in a ViewPager2 View.
 * @see https://developer.android.com/reference/androidx/viewpager2/adapter/FragmentStateAdapter
 */
class ReservationsPagerAdapter(fa: Fragment) : FragmentStateAdapter(fa) {

    /**
     * Return the number of views available
     */
    override fun getItemCount(): Int = 2

    /**
     * Provide a new fragment associated with a specified position
     * @return A custom fragment
     */
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PlannedReservationsFragment()
            else -> {
                PastReservationsFragment()
            }
        }
    }
}
