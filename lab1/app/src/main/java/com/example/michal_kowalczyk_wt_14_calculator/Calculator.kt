package com.example.michal_kowalczyk_wt_14_calculator

import android.widget.TextView
import java.util.ArrayList

class Calculator : CalculatorInterface {

    private  var tvResult:TextView
    private var result:Double = 0.0
    private  var eqation: String
    private  var operatorList:ArrayList<Char>
    private  var numbersList:ArrayList<Double>






    constructor(result: TextView){
        this.tvResult = result
        this.eqation=""
        this.operatorList = ArrayList()
        this.numbersList = ArrayList()


    }

    override fun evaluate()  {


        val isEquationParsed = parseEquation()
        var tmpResult:Double = result
        if (isEquationParsed){


            if (numbersList.size ==1){

                this.result = numbersList.get(0)

                operatorList.clear()
                numbersList.clear()

                return

            }

            while (!operatorList.isEmpty()){
                var op:Char
                if (operatorList.contains('/') || operatorList.contains('*')){
                    var indDiv = operatorList.indexOf('/')
                    var indMul = operatorList.indexOf('*')

                    if (indDiv>-1 && indMul>-1 ) {
                        if (indDiv<indMul) op = '/'
                        else op='*'
                    }else{
                        if(indDiv < indMul) op='*'
                        else op='/'
                    }

                }else{
                    op = operatorList.get(0)

                }

                var index = operatorList.indexOf(op)

                when(op){
                    '+' -> {
                        tmpResult = numbersList.get(index) + numbersList.get(index+1)
                        numbersList.removeAt(index)
                        numbersList.set(index,tmpResult)
                    }
                    '-' -> {
                        tmpResult = numbersList.get(index) - numbersList.get(index+1)
                        numbersList.removeAt(index)
                        numbersList.set(index,tmpResult)
                    }
                    '*' -> {
                        tmpResult = numbersList.get(index) * numbersList.get(index+1)
                        numbersList.removeAt(index)
                        numbersList.set(index,tmpResult)
                    }
                    '/' -> {
                        tmpResult = numbersList.get(index) / numbersList.get(index+1)
                        numbersList.removeAt(index)
                        numbersList.set(index,tmpResult)
                    }

                }


                operatorList.remove(op)
            }



            result = tmpResult
            operatorList.clear()
            numbersList.clear()

            eqation = this.result.toString()
            
            this.tvResult.setText( this.result.toString())
        }








    }

    private fun parseEquation():Boolean {
        var  operands:String = "[-+*/]"
        var currentNumber:String=""

        if (eqation.isEmpty()){
            System.out.println("Puste równanie")
            return false;
        }else if (operands.contains(eqation.get(eqation.length-1))){
            System.out.println("Błędne równanie")
            return false;
        }

        if (eqation.get(0) == '-'){
            currentNumber +='-'
            eqation = eqation.substring(1)
        }


        for(ch in eqation) {
            if (operands.contains(ch)){
                operatorList.add(ch)
                numbersList.add(currentNumber.toDouble())
                currentNumber=""
            }else{
                currentNumber += ch
            }

        }


        if (currentNumber != ""){
            numbersList.add(currentNumber.toDouble())
        }






    return true
    }

    override fun clear() {
        this.eqation =""
        this.result = 0.0
        this.tvResult.setText("0")
    }

    override fun percent() {
        evaluate()

        this.result = this.result/100
        this.eqation= this.result.toString()

        tvResult.setText( this.result.toString())
    }

    override fun sqrt() {
        evaluate()
        this.result = Math.sqrt(this.result)
        this.eqation= this.result.toString()
        tvResult.setText( this.result.toString())
    }


     override fun pushDigit(char: Char) {
        this.eqation += char

         this.tvResult.setText(eqation)
    }

    override fun pushOperation(char: Char) {

        var  operands:String = "[-+*/.]"

        if (eqation.isEmpty() )return
        else{
            if(operands.contains(eqation.get(eqation.length-1))){

                eqation = eqation.substring(0,eqation.length-1)+char
                this.tvResult.setText(eqation)
                return

            }



        }



        this.eqation += char

        this.tvResult.setText(eqation)
    }

}



