package com.example.squads.screens.exercises

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.squads.R
import com.example.squads.databinding.FragmentExerciseDetailBinding
import com.example.squads.screens.exercises.models.ExerciseType
import com.example.squads.screens.exercises.models.PersonalRecord

class ExerciseDetailFragment(private val exercise: ExerciseType) : Fragment() {

    private lateinit var viewModel: ExerciseDetailViewModel
    lateinit var binding: FragmentExerciseDetailBinding

    constructor() : this(ExerciseType(-1, "", "", ""))

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_exercise_detail, container, false)
        val vm: ExerciseDetailViewModel by activityViewModels()
        viewModel = vm
        var imgId: Int = context?.resources?.getIdentifier(exercise.imageUrl, "drawable", context?.packageName) ?: -1
        binding.exerciseImage.setImageResource(imgId)

        binding.prViewModel = viewModel

        binding.prsExplanationText.text = exercise.explanation

        addRecords()

        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        Log.d("ExerciseDetailFragment", "resume...")
    }

    private fun addRecords() {
        var records: List<PersonalRecord> = viewModel.getRecordsOfTypeAndUser(exercise.id, 1)

        records.forEach {

            val inflater: LayoutInflater = LayoutInflater.from(context)
            val record: View = inflater.inflate(R.layout.personal_record, null, false)

            binding.prsRecords.addView(record)

//            val ll = LinearLayout(context)
//            ll.orientation = LinearLayout.HORIZONTAL
//            ll.setPadding(20)
//            ll.setBackgroundResource(R.drawable.gray_gradiant_button_upside_down)
//            ll.layoutParams = ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
//
//            val tv1 = TextView(context)
//            tv1.text = it.amountOfReps.toString().plus(" rep(s)")
//            tv1.setTextColor(Color.parseColor("#FFFFFF"))
//            ll.addView(tv1)
//
//            val tv2 = TextView(context)
//            tv2.text = it.achievedOn.toString()
//            tv2.setTextColor(Color.parseColor("#FFFFFF"))
//            ll.addView(tv2)
//
//            val tv3 = TextView(context)
//            tv3.text = it.weightUsed.toString().plus(" kg")
//            tv3.setTextColor(Color.parseColor("#FFFFFF"))
//            ll.addView(tv3)
//
//            val space = Space(context)
//            space.layoutParams = ViewGroup.LayoutParams(0, 40)
//            binding.prsRecords.addView(ll)
//            binding.prsRecords.addView(space)
        }
    }
}
