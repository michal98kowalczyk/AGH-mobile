package com.example.michal_kowalczyk_wt_14.ownactivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.widget.doOnTextChanged
import com.example.michal_kowalczyk_wt_14.R
import com.example.michal_kowalczyk_wt_14.ownactivities.helpers.ExchangeRatesFetcher

class ExchangeActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var form: LinearLayout
    private lateinit var barLoad: ProgressBar

    private lateinit var pln: EditText
    private lateinit var other: EditText

    private lateinit var select: Spinner
    private var choice = 0
    private val fetcher = ExchangeRatesFetcher(this)
    private var isSet = false

    private lateinit var errorMsg: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle(R.string.cantorName)
        setContentView(R.layout.activity_exchange)

        form = findViewById(R.id.llwrapper)
        errorMsg = findViewById(R.id.tvError)
        barLoad = findViewById(R.id.barLoad)
        pln = findViewById(R.id.plnValue)
        other = findViewById(R.id.otherValue)
        select = findViewById(R.id.btnSelect)

        fetcher.handleLoad = {
            setCurrency()


            form.visibility = View.VISIBLE
            barLoad.visibility = View.GONE
            errorMsg.visibility = View.GONE

            pln.doOnTextChanged { text, start, count, after ->
                if (!text?.isEmpty()!! && !isSet) {
                    val rate = pln.text.toString().toFloat() / fetcher.allRates[choice].mid
                    isSet = !isSet
                    other.setText(rate.toString())
                    isSet = !isSet
                }
            }

            other.doOnTextChanged { text, start, count, after ->
                if (!text?.isEmpty()!! == false && !isSet) {
                    val rate = other.text.toString().toFloat() * fetcher.allRates[choice].mid
                    isSet = !isSet
                    pln.setText(rate.toString())
                    isSet = !isSet
                }
            }
        }

        fetcher.handleError = {

            form.visibility = View.GONE
            barLoad.visibility = View.GONE
            errorMsg.visibility = View.VISIBLE

        }


        form.visibility = View.GONE
        barLoad.visibility = View.VISIBLE
        errorMsg.visibility = View.GONE

        fetcher.fetch()
    }

    fun setCurrency() {


        ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                fetcher.allRates.map { rate -> rate.code }
        ).also { adapter ->

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            select.adapter = adapter
            select.onItemSelectedListener = this
            select.setSelection(choice)
        }
    }





    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        choice = pos
        val rate = other.text.toString().toFloat() * fetcher.allRates[choice].mid

        isSet = !isSet
        pln.setText(rate.toString())
        isSet = !isSet
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }



}