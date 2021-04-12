package com.example.michal_kowalczyk_wt_14.ownactivities.helpers.entities

data class PreviousRates (
    val table: String,
    val currency: String,
    val code: String,
    val rates: MutableList<RateEntity>
)