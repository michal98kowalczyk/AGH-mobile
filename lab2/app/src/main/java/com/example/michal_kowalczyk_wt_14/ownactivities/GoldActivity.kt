package com.example.michal_kowalczyk_wt_14.ownactivities

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.michal_kowalczyk_wt_14.R
import com.example.michal_kowalczyk_wt_14.ownactivities.helpers.entities.GoldRateEntity
import com.example.michal_kowalczyk_wt_14.fromJson
import com.example.michal_kowalczyk_wt_14.ownactivities.helpers.formatters.GoldRatesFormatter
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.gson.Gson


class GoldActivity : AppCompatActivity() {
    private var rates = mutableListOf<GoldRateEntity>()

    private lateinit var currentRate: TextView
    private lateinit var monthRate: LineChart



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle(R.string.goldName)
        setContentView(R.layout.activity_gold_rates)


        currentRate = findViewById(R.id.tvCurrentRate)
        monthRate = findViewById(R.id.imgMonthRates)


        monthRate.xAxis.labelRotationAngle = -90f
        monthRate.xAxis.position = XAxis.XAxisPosition.BOTTOM
        monthRate.extraBottomOffset = 45f
        monthRate.legend.isEnabled = false
        monthRate.description.isEnabled = false


        makeRequest()
    }

    fun makeRequest() {
        val url = "https://api.nbp.pl/api/cenyzlota/last/30?format=json"

        val queue = Volley.newRequestQueue(this)
        val result = JsonArrayRequest(
                Request.Method.GET, url, null,
                { response ->
                    val data = Gson().fromJson<MutableList<GoldRateEntity>>(response.toString())
                    rates = data

                    currentRate.text = "${rates[0].cena}PLN"

                    val monthData = rates.slice(0..29).reversed().mapIndexed { index, rate ->
                        Entry(
                                index.toFloat(),
                                rate.cena
                        )
                    }

                    monthRate.data = LineData(LineDataSet(monthData, ""))
                    monthRate.xAxis.valueFormatter = GoldRatesFormatter(rates)
                    monthRate.invalidate()
                },
                { error ->
                    currentRate.text = getText(R.string.sError)
                    Log.d("Error", error.toString())
                }
        )

        currentRate.text = getText(R.string.sIsLoading)
        queue.add(result)
    }




}