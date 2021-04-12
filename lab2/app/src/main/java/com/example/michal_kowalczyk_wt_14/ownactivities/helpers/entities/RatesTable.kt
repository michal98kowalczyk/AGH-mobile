package com.example.michal_kowalczyk_wt_14.ownactivities.helpers.entities

data class RatesTable(
    val table: Char,
    val no: String,
    val effectiveDate: String,
    val rates: List<Rate>
)