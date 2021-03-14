package com.example.michal_kowalczyk_wt_14_calculator

import android.widget.TextView

class Calculator : CalculatorInterface {

    private lateinit var tvResult:TextView
    private var result:Double = 0.0
    private lateinit var eqation: String


    constructor(result: TextView){
        this.tvResult = result
        this.eqation=""
    }

    override fun evaluate() {
        this.tvResult.text = this.result.toString()
    }

    override fun clear() {
        this.eqation =""
        this.tvResult.text = "0"
    }

    override fun add() {
        TODO("Not yet implemented")
    }

    override fun subtract() {
        TODO("Not yet implemented")
    }

    override fun multiply() {
        TODO("Not yet implemented")
    }

    override fun divide() {
        TODO("Not yet implemented")
    }

    override fun dot() {
        TODO("Not yet implemented")
    }

    override fun percent() {
        TODO("Not yet implemented")
    }

    override fun sqrt() {
        TODO("Not yet implemented")
    }

     fun pushDigit(char: String) {
        this.eqation += char

         this.tvResult.text = eqation
    }

    fun pushOperation(char: String) {
        this.eqation += char

        this.tvResult.text = eqation
    }

}