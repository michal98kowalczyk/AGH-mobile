package com.example.michal_kowalczyk_wt_14.ownactivities.helpers.entities

data class Rate(
        val currency: String,
        val code: String,
        val mid: Float,
        val isGrown: Boolean = false,
        val table: String = "a"
)