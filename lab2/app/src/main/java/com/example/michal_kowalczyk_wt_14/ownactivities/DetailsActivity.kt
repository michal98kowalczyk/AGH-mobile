package com.example.michal_kowalczyk_wt_14.ownactivities

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.michal_kowalczyk_wt_14.R
import com.example.michal_kowalczyk_wt_14.ownactivities.helpers.entities.PreviousRates
import com.example.michal_kowalczyk_wt_14.ownactivities.helpers.entities.RateEntity
import com.example.michal_kowalczyk_wt_14.fromJson
import com.example.michal_kowalczyk_wt_14.ownactivities.helpers.formatters.ExchangeFormatter
import com.example.michal_kowalczyk_wt_14.ownactivities.helpers.Messages
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.gson.Gson

class DetailsActivity : AppCompatActivity() {
    private lateinit var code: String
    private lateinit var table: String
    private lateinit var rate: TextView
    private lateinit var lastRate: TextView
    private lateinit var weekRate: LineChart
    private lateinit var monthRate: LineChart

    private var ratesList = mutableListOf<RateEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle(intent.getStringExtra(Messages.DISPLAYED_CURRENCY_NAME)
                ?.capitalize()

        )
        setContentView(R.layout.activity_rate_details)



        rate = findViewById(R.id.tvCurrentRate)
        lastRate = findViewById(R.id.tvLastRate)




        weekRate = findViewById(R.id.imgWeekRates)
        monthRate = findViewById(R.id.imgMonthRates)

        weekRate.description.isEnabled = false
        weekRate.xAxis.labelRotationAngle = -90f
        weekRate.extraBottomOffset = 45f
        weekRate.xAxis.position = XAxis.XAxisPosition.BOTTOM
        weekRate.legend.isEnabled = false

        monthRate.description.isEnabled = false
        monthRate.xAxis.labelRotationAngle = -90f
        monthRate.extraBottomOffset = 45f
        monthRate.xAxis.position = XAxis.XAxisPosition.BOTTOM
        monthRate.legend.isEnabled = false


        code = intent.getStringExtra(Messages.DISPLAYED_CURRENCY_CODE) ?: ""
        table = intent.getStringExtra(Messages.DISPLAYED_CURRENCY_TABLE) ?: ""
        makeRequest()
    }

    fun makeRequest() {
        val url = "https://api.nbp.pl/api/exchangerates/rates/${table}/$code/last/30/?format=json"

        val queue = Volley.newRequestQueue(this)


        val result = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val data = Gson().fromJson<PreviousRates>(response.toString())
                ratesList = data.rates

                rate.text = "${ratesList[0].mid} PLN"
                lastRate.text = "${ratesList[1].mid} PLN"

                val weekData = ratesList.slice(0..6).reversed().mapIndexed { index, rate ->
                    Entry(
                        index.toFloat(),
                        rate.mid
                    )
                }

                weekRate.data = LineData(LineDataSet(weekData, ""))
                weekRate.xAxis.valueFormatter = ExchangeFormatter(ratesList)
                weekRate.invalidate()


                val monthData = ratesList.slice(0..29).reversed().mapIndexed { index, rate ->
                    Entry(
                        index.toFloat(),
                        rate.mid
                    )
                }

                monthRate.data = LineData(LineDataSet(monthData, ""))
                monthRate.xAxis.valueFormatter = ExchangeFormatter(ratesList)
                monthRate.invalidate()
            },
            { error ->
                rate.text = getText(R.string.sError)
                lastRate.text = getText(R.string.sError)
                Log.d("Error", error.toString())
            }
        )

        rate.text = getText(R.string.sIsLoading)
        lastRate.text = getText(R.string.sIsLoading)
        queue.add(result)
    }




}