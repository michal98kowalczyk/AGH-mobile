package com.example.michal_kowalczyk_wt_14_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TableLayout

class MainActivity : AppCompatActivity() {


    private lateinit var calculator:Calculator
    private lateinit var calcTableLayout: TableLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calcTableLayout = findViewById(R.id.tlCalc)
        calculator = Calculator(findViewById(R.id.tvResult))

        for(btn in calcTableLayout.touchables){
            addOnClick(btn)
        }


    }

    private fun addOnClick(btn: View) {

        try {


        when(btn.id){
            R.id.b1 -> btn.setOnClickListener({view -> calculator.pushDigit("1")})
            R.id.b2 -> btn.setOnClickListener({view -> calculator.pushDigit("2")})
            R.id.b3 -> btn.setOnClickListener({view -> calculator.pushDigit("3")})
            R.id.b4 -> btn.setOnClickListener({view -> calculator.pushDigit("4")})
            R.id.b5 -> btn.setOnClickListener({view -> calculator.pushDigit("5")})
            R.id.b6 -> btn.setOnClickListener({view -> calculator.pushDigit("6")})
            R.id.b7 -> btn.setOnClickListener({view -> calculator.pushDigit("7")})
            R.id.b8 -> btn.setOnClickListener({view -> calculator.pushDigit("8")})
            R.id.b9 -> btn.setOnClickListener({view -> calculator.pushDigit("9")})
            R.id.b0 -> btn.setOnClickListener({view -> calculator.pushDigit("0")})
            R.id.bPercent -> btn.setOnClickListener({view -> calculator.percent()})
            R.id.bSqrt -> btn.setOnClickListener({view -> calculator.sqrt()})
            R.id.bDivide -> btn.setOnClickListener({view -> calculator.pushOperation("/")})
            R.id.bMultiply -> btn.setOnClickListener({view -> calculator.pushOperation("*")})
            R.id.bAddition -> btn.setOnClickListener({view -> calculator.pushOperation("+")})
            R.id.bSubtraction -> btn.setOnClickListener({view -> calculator.pushOperation("-")})
            R.id.bDot -> btn.setOnClickListener({view -> calculator.pushOperation(".")})
            R.id.bEqual -> btn.setOnClickListener({view -> calculator.evaluate()})
            R.id.bClear -> btn.setOnClickListener({view -> calculator.clear()})

            }
        }catch (error : Exception){
            System.out.println("Wrong equation!!!")
        }

    }


}