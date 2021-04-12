package com.example.michal_kowalczyk_wt_14.ownactivities.helpers.formatters

import com.example.michal_kowalczyk_wt_14.ownactivities.helpers.entities.RateEntity
import com.github.mikephil.charting.formatter.ValueFormatter

class ExchangeFormatter(private var rates: MutableList<RateEntity>) : ValueFormatter() {
    override fun getFormattedValue(value: Float): String? {
        return rates.getOrNull(value.toInt())?.effectiveDate ?: ""
    }
}