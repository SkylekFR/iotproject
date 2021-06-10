package com.example.iotproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.iotproject.model.SensorData
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.*
import com.google.firebase.database.FirebaseDatabase


class StatsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stats, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = StatsFragment()
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