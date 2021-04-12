//Autor Michał Kowalczyk gr. wt 14.00
//obsluga obu tabel
//lista walut z recycler View
//w elemencie listy jest flaga, kod, kurs i strzałka czy spadł czy nie
//poprawiony blad z dopasowaniem flagi do waluty
//dodatkowe activity dla szczegółów danej waluty
//
//kurs zlota + wykres
//
//kantor obustronny
//
//






package com.example.michal_kowalczyk_wt_14

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.michal_kowalczyk_wt_14.ownactivities.ExchangeActivity
import com.example.michal_kowalczyk_wt_14.ownactivities.GoldActivity
import com.example.michal_kowalczyk_wt_14.ownactivities.RatesActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }



    fun handleGoldClick(view: View) {
        val intent = Intent(this, GoldActivity::class.java)

        startActivity(intent)
    }

    fun handleRatesClick(view: View) {
        val intent = Intent(this, RatesActivity::class.java)

        startActivity(intent)
    }

    fun handleExchangeClick(view: View) {
        val intent = Intent(this, ExchangeActivity::class.java)

        startActivity(intent)
    }
}