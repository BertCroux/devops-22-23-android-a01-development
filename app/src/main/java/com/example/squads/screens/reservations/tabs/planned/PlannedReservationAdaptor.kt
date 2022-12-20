package com.example.squads.screens.reservations.tabs.planned

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.squads.R
import com.example.squads.databinding.PlannedReservationBinding
import com.example.squads.screens.reservations.Reservation

class PlannedReservationAdaptor(private val dataSet: LiveData<List<Reservation>>?) :
    RecyclerView.Adapter<PlannedReservationAdaptor.ViewHolder>() {
    lateinit var context : Context

    lateinit var binding: PlannedReservationBinding
    inner class ViewHolder(val binding: PlannedReservationBinding) : RecyclerView.ViewHolder(binding.root)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        binding = PlannedReservationBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        context = viewGroup.context

        return ViewHolder(binding)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        binding.textView3.text = dataSet?.value!![position].trainerType
        binding.trainerText.text = dataSet.value!![position].trainerName
        binding.workoutDateText.text = context.getString(R.string.workoutdate_text,
            dataSet.value!![position].beginDate.hour,
            dataSet.value!![position].beginDate.minute,
            dataSet.value!![position].endDate.hour,
            dataSet.value!![position].endDate.minute,
            )
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet?.value?.size ?: 0
}
