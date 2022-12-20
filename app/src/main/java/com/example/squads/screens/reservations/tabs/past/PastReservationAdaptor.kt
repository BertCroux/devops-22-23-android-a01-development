package com.example.squads.screens.reservations.tabs.past

import android.content.Context
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.example.squads.R
import com.example.squads.databinding.PastReservationBinding
import com.example.squads.screens.reservations.Reservation

class PastReservationAdaptor(private val dataSet: LiveData<List<Reservation>>?) :
    RecyclerView.Adapter<PastReservationAdaptor.ViewHolder>() {

    lateinit var binding: PastReservationBinding
    lateinit var context : Context
    inner class ViewHolder(val binding: PastReservationBinding) : RecyclerView.ViewHolder(binding.root)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        binding = PastReservationBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        context = viewGroup.context

        return ViewHolder(binding)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        binding.textView3.text = dataSet?.value!![position].trainerName
        binding.textView2.text = context.getString(R.string.past_reservation_text,
            dataSet.value!![position].beginDate.dayOfMonth,
        dataSet.value!![position].beginDate.monthNumber,
        dataSet.value!![position].beginDate.year)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet?.value?.size ?: 0
}
