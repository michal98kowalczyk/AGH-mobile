package com.example.michal_kowalczyk_wt_14.ownactivities.helpers

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.michal_kowalczyk_wt_14.ownactivities.helpers.entities.Rate
import com.example.michal_kowalczyk_wt_14.ownactivities.helpers.entities.RatesTable
import com.example.michal_kowalczyk_wt_14.fromJson
import com.google.gson.Gson


class ExchangeRatesFetcher(val context: Context) {
    var allRates = mutableListOf<Rate>()
    var handleLoad: (()->Unit)? = null
    var handleError: (()->Unit)? = null



    fun fetch() {


        val queue = Volley.newRequestQueue(context)
        val rates = mutableListOf<Rate>()

        var count = 2
        var isFetched = false
        fun makeRequest(table: String): JsonArrayRequest {

            return JsonArrayRequest(
                    Request.Method.GET, "https://api.nbp.pl/api/exchangerates/tables/${table}/last/2?format=json", null,
                    { response ->
                        val data = Gson().fromJson<List<RatesTable>>(response.toString())
                        val fetchedRates = data[0].rates.zip(data[1].rates) {
                            last, current -> Rate(
                                current.currency,
                                current.code,
                                current.mid,
                                last.mid < current.mid,
                                table
                        )
                        }

                        rates.addAll(fetchedRates)

                        count-=1

                        if (!isFetched && count == 0  ) {

                            allRates.clear()

                            allRates.addAll(
                                    rates.sortedBy { rate -> rate.code }
                                            .distinctBy { rate -> rate.code }
                            )

                            handleLoad?.invoke()
                        }
                    },
                    { error ->
                        handleError?.invoke()
                        isFetched = true
                        Log.d("Error", error.toString())
                    }
            )
        }



        queue.add(makeRequest("a"))
        queue.add(makeRequest("b"))
    }





}