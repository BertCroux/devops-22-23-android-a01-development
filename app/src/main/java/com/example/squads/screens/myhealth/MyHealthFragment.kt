package com.example.squads.screens.myhealth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.squads.R
import com.example.squads.databinding.FragmentMyHealthBinding

class MyHealthFragment : Fragment() {

    lateinit var binding: FragmentMyHealthBinding
    lateinit var myHealthViewModel: MyHealthViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // get binding object and inflate the fragments
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_my_health, container, false
        )

        // get the viewmodel
        val vm: MyHealthViewModel by lazy {
            val activity = requireNotNull(this.activity)
            ViewModelProvider(this, MyHealthViewModel.Factory(activity.application)).get(MyHealthViewModel::class.java)
        }
        // set the viewmodel
        myHealthViewModel = vm
        // set the viewmodel in the xml file
        binding.myHealthViewModel = myHealthViewModel

        // makes the live data work ig
        binding.lifecycleOwner = this

        addObservers()

        return binding.root
    }

    private fun addObservers() {
        // observer for the latest measurement
        myHealthViewModel.latestMeasurement.observe(
            viewLifecycleOwner,
            Observer { measurement ->
                // if the latestMeasurement changes, all textfields must be updated aswell
                measurement.let {
                    binding.txtWeightValue.text = String.format("%.1f", it.weight)
                    binding.txtFatValue.text = String.format("%.1f", it.fatPercentage)
                    binding.txtMuscleValue.text = String.format("%.1f", it.musclePercentage)
                    binding.txtWaistCircValue.text = String.format("%.1f", it.waistCircumference)
                    // TODO BMI: moet nog lengte van persoon hebben
                    var bmi = it.weight / (1.7 * 1.7)
//                Log.i("graphs", bmi.toString())
                    binding.txtBMIValue.text = String.format("%.1f", bmi)
                    // to fit in the slider, the bmi must be between 15.5 and 27.9 and should be rounded
                    if (bmi < 15.5) bmi = 15.5
                    if (bmi > 27.9) bmi = 27.9
                    binding.sliderEffectieveWaarde.value = (Math.round(bmi * 10.0) / 10).toFloat()
                }
            }
        )

        // observer on when to navigate to the graphs fragment
        myHealthViewModel.navigateToGraphs.observe(
            viewLifecycleOwner,
            Observer {
                // actually navigate to the graphs page
                if (it == true) {
                    this.findNavController().navigate(R.id.action_myhealth_to_myHealthGraphsFragment)
                    myHealthViewModel.doneNavigatingToGraphs()
                }
            }
        )
    }
}