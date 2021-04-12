package com.example.michal_kowalczyk_wt_14.ownactivities.helpers.formatters

import com.example.michal_kowalczyk_wt_14.ownactivities.helpers.entities.GoldRateEntity
import com.github.mikephil.charting.formatter.ValueFormatter

class GoldRatesFormatter(private var rates: MutableList<GoldRateEntity>) : ValueFormatter() {
    override fun getFormattedValue(value: Float): String? {
        return rates.getOrNull(value.toInt())?.data ?: ""
    }
}