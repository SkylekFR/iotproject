package com.example.iotproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StatsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StatsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stats, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StatsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StatsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val reference = FirebaseDatabase.getInstance().reference
        val listHumidity = arrayListOf<BarEntry>()
        val listTemperature = arrayListOf<BarEntry>()
        var barchart = view.findViewById<BarChart>(R.id.barchart);
        var incrementer = 0f;
        reference.child("/cave/sensor_inside/append").limitToLast(10).get()
            .addOnSuccessListener { snapshot ->
                for (dataSnapshot in snapshot.children) {
                    val sensorData = dataSnapshot.getValue(SensorData::class.java)
                    listHumidity.add(BarEntry(incrementer, sensorData!!.humidity))
                    listTemperature.add(BarEntry(incrementer++, sensorData.temperature))
                    // do something with object
                }
                var dataSetHumidity = BarDataSet(listHumidity, "Humidity")
                var dataSetTemperature = BarDataSet(listTemperature, "Temperature")
                dataSetHumidity.color = context?.getColor(R.color.purple_200) ?: 0
                dataSetTemperature.color = context?.getColor(R.color.purple_700) ?: 0
                var barData = BarData(dataSetHumidity, dataSetTemperature)
                barchart.data = barData
                barData.barWidth = 0.45f
                barchart.groupBars(0f, 0.06f, 0.02f)
                barchart.invalidate()
            }
            .addOnCanceledListener {

            }


    }
}