package com.example.michal_kowalczyk_wt_14.ownactivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blongho.country_data.World
import com.example.michal_kowalczyk_wt_14.R
import com.example.michal_kowalczyk_wt_14.ownactivities.helpers.entities.Rate
import com.example.michal_kowalczyk_wt_14.ownactivities.helpers.adapters.RatesAdapter
import com.example.michal_kowalczyk_wt_14.ownactivities.helpers.ExchangeRatesFetcher

class RatesActivity : AppCompatActivity() {
    lateinit var ratesAdapter: RatesAdapter
    lateinit var recycler: RecyclerView
    lateinit var barLoad: ProgressBar
    private val fetcher = ExchangeRatesFetcher(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        World.init(this);
        setTitle(R.string.ratesName)
        setContentView(R.layout.activity_rates_list)

        barLoad = findViewById(R.id.barLoad)
        recycler = findViewById(R.id.rvRates)

        displayList()
    }

    fun displayList() {

        ratesAdapter = RatesAdapter(fetcher.allRates, this)


        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = ratesAdapter




        fetcher.handleLoad = {
            ratesAdapter.notifyDataSetChanged()


            barLoad.visibility = View.GONE
            recycler.visibility = View.VISIBLE
        }

        fetcher.handleError = {
            val title = getText(R.string.sError).toString()
            fetcher.allRates = mutableListOf(Rate(title, "", 0.0f))
            ratesAdapter.notifyDataSetChanged()


            barLoad.visibility = View.GONE
            recycler.visibility = View.VISIBLE
        }

        recycler.visibility = View.GONE
        barLoad.visibility = View.VISIBLE

        fetcher.fetch()
    }




}