package com.example.counter

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(){



    private var counterValue=0
    private var isStarted = false;
    internal lateinit var btnCounter:Button
    internal lateinit var tvTime:TextView
    internal lateinit var tvCount:TextView

    val countDownTimer = object : CountDownTimer(10000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            tvTime.setText("" + millisUntilFinished / 1000)
        }

        override fun onFinish() {
//            tvTime.setText("0")
            btnCounter.setText("Start")

            counterValue=0
            isStarted=false

        }
    }


override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCounter = findViewById(R.id.btnCounter)
        tvTime = findViewById(R.id.tvTime)
        tvCount = findViewById(R.id.tvCount)

        btnCounter.setOnClickListener({ view -> clickButton()})

    }

    private fun clickButton() = if(!isStarted){
        isStarted = !isStarted



        btnCounter.setText("Tap me")
        tvCount.setText("0")


        countDownTimer.start()
//        tvTime.setText("10")
    }else{

        counterValue +=1
        tvCount.setText(""+counterValue)

    }
}