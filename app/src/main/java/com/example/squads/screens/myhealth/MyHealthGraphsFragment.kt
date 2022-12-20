package com.example.squads.screens.myhealth
import android.annotation.SuppressLint
import android.content.res.Resources.NotFoundException
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.graphics.toColorInt
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.androidplot.xy.*
import com.example.squads.R
import com.example.squads.databinding.FragmentMyHealthGraphsBinding
import com.example.squads.domain.measurements.Measurement
import kotlinx.datetime.LocalDateTime
import java.text.FieldPosition
import java.text.Format
import java.text.ParsePosition
import java.util.Date

class MyHealthGraphsFragment : Fragment() {

    var measurements: List<Measurement>? = null

    var latestMeasurement: Measurement? = null

    //the list of values to display
    var valuesForGraph: List<Pair<Double, Date>>? = null

    //filtered on year (gets initialised by the spinner)
    var valuesForGraphFiltered: List<Pair<Double, Date?>> = emptyList()

    lateinit var binding: FragmentMyHealthGraphsBinding

    lateinit var myHealthViewModel: MyHealthViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_my_health_graphs, container, false
        )

        //get the viewmodel
        val vm: MyHealthViewModel by activityViewModels()
        //set the viewmodel
        myHealthViewModel = vm
        // set the viewmodel in the xml file
        binding.myHealthViewModel = myHealthViewModel

        //makes the live data work ig
        binding.lifecycleOwner = this

        binding.myHealthGraphsFragment = this@MyHealthGraphsFragment


        addObservers()
        setupSpinner()

        return binding.root
    }

    @SuppressLint("ResourceType") //suppress warning R.color.orange and R.color.darkorange
    private fun setupPlot() {
        val plot = binding.plot
        plot.clear()

        val domainLabels = valuesForGraphFiltered.map { it ->
            String.format("%d-%s", LocalDateTime.parse(it.second.toString()).dayOfMonth, LocalDateTime.parse(it.second.toString()).monthNumber)
        }


//        Log.i("graphs", "labels:--------------------")
//        domainLabels.forEach {
//            Log.i("graphs", it.toString())
//        }

        val arr: Array<Number> = valuesForGraphFiltered.map { it.first }.toTypedArray()

//        Log.i("graphs", "values:--------------------")
//        arr.forEach {
//            Log.i("graphs", it.toString())
//        }

        val series1: XYSeries = SimpleXYSeries(
            listOf(* arr),
            SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,
            "Series 1"
        )

        //weird but I have to do this to get the hex value to convert it to color int
        val orange = resources.getString(R.color.orange).toColorInt()
        val darkorange = resources.getString(R.color.darkorange).toColorInt()

        val series1Format =
            LineAndPointFormatter(orange, darkorange, null, null)


        //optional: to set the lines to smooth or straight
//        series1Format.setInterpolationParams(
//            CatmullRomInterpolator.Params(
//                10,
//                CatmullRomInterpolator.Type.Centripetal
//            )
//        )

        plot.addSeries(series1, series1Format)
        plot.legend.isVisible = false //remove the legend
        plot.setDomainStep(StepMode.INCREMENT_BY_FIT, 1.0)
        plot.graph.getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).format = object : Format() {
            override fun format(
                obj: Any?,
                toAppendTo: StringBuffer,
                pos: FieldPosition
            ): StringBuffer {
                val i = Math.round((obj as Number).toFloat())
                val value = domainLabels[i]
                return toAppendTo.append(value)
            }

            override fun parseObject(source: String?, pos: ParsePosition?): Any? {
                // do nothing really
                return null
            }
        }

        //optional to pan and zoom
//        PanZoom.attach(plot)

    }

    fun navigateBack() {
        this.findNavController().navigate(R.id.action_myHealthGraphsFragment_to_myhealth)
    }

    private fun setupSpinner() {
        //get the spinner from xml
        val spinner: Spinner = binding.spinnerHealthYears
        //create a list of items to put in the spinner
        val spinnerItems: List<String> = getDistinctYearsFromMeasurements()
        //create an adapter to insert the values and use a custom view (health_spinner_item) to render the items
        val spinnerAdapter =
            ArrayAdapter(requireActivity(), R.layout.health_spinner_item, spinnerItems)
        spinner.adapter = spinnerAdapter

        //override the onselect listeners
        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val value = parent.getItemAtPosition(position)

//                Log.i("graphs", "lijst voor filtering---------------")
//                logValuesForGraph()

                //effectief de lijst filteren en sorteren
                valuesForGraphFiltered =
                    (valuesForGraph?.filter { it.second?.year.toString() == value }?.sortedBy { it.second }
                        ?:

                //                Log.i("graphs", "lijst na filtering---------------")
                //                logValuesForGraphFiltered()

                        setupPlot( )) as List<Pair<Double, Date?>>
                binding.plot.invalidate() //force redraw the view
            }

            //has to be implemented even though you don't use it
            override fun onNothingSelected(p0: AdapterView<*>) {
                //do nothing...
            }
        }
    }

    private fun addObservers() {
        //get the measurements from the viewmodel
        measurements = myHealthViewModel.measurements.value
        myHealthViewModel.measurements.observe(viewLifecycleOwner, Observer {
            measurements = it
        })

        //get the latest measurement from the viewmodel
        latestMeasurement = myHealthViewModel.latestMeasurement.value

        //when the button is clicked, a type is sent along with it as string and is stored in the viewmodel
        //so when the type in the viewmodel changes, the measurements have to be mapped to the right type
        // in this observer function
        valuesForGraph = emptyList()
        myHealthViewModel.typeDataGraph.observe(viewLifecycleOwner, Observer { type ->
            if (type != null) {
                //set lateinit var and update the text field to display the type
                binding.txtCurrentType.text = type
                valuesForGraph = mapMeasurements(type)
            }
        })
    }

    //get the years to display in the spinner
    private fun getDistinctYearsFromMeasurements(): List<String> {
        return measurements?.map {
            it.measuredOn.toString()
        }?.distinct()?.sorted()?.reversed()?.toMutableList() ?: emptyList()
    }

    /**
     * This function sets the text value to the latest measurement of that type
     * @return a list of pairs (tuples) that contain the value and the date of measurement
     */
    private fun mapMeasurements(type: String): List<Pair<Double, Date>>? {
        when (type) {
            "Weight" -> {
                binding.txtLatestValue.text = latestMeasurement?.let { String.format("%.1f kg", it.weight) }
                return measurements?.map { Pair(it.weight, it.measuredOn) }
            }
            "Fat" -> {
                binding.txtLatestValue.text =
                    latestMeasurement?.let { String.format("%.1f %%", it.fatPercentage) }
                return measurements?.map { Pair(it.fatPercentage, it.measuredOn) }
            }
            "Muscle" -> {
                binding.txtLatestValue.text =
                    latestMeasurement?.let { String.format("%.1f %%", it.musclePercentage) }
                return measurements?.map { Pair(it.musclePercentage, it.measuredOn) }
            }
            "Waist" -> {
                binding.txtLatestValue.text =
                    latestMeasurement?.let { String.format("%.1f cm", it.waistCircumference) }
                return measurements?.map { Pair(it.waistCircumference, it.measuredOn) }
            }
            "BMI" -> {
                //TODO BMI
                print("apart geval")
                binding.txtLatestValue.text =
                    String.format("%.1f", (latestMeasurement?.weight)?.div((1.7*1.7)) ?: "")
                return measurements?.map { Pair(it.weight / (1.8*1.8), it.measuredOn)}
            }
            else -> {
                throw NotFoundException()
            }
        }
    }


    //helper functions for debugging purposes
//    private fun logValuesForGraph() {
//        valuesForGraph.forEach {
//            Log.i("graphs", it.toString())
//        }
//    }
//
//    private fun logValuesForGraphFiltered() {
//        valuesForGraphFiltered.forEach {
//            Log.i("graphs", it.toString())
//        }
//    }
}