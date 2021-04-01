package com.karlosarr.calculadora

class StringCalculator {
    fun calculate(calculationList: List<String>): Int {
        var currentOp: String = ""
        var currentNumber = 0

        calculationList.forEach { token ->
            when {
                token.equals("+")
                        || token.equals("/")
                        || token.equals("*")
                        || token.equals("-") -> currentOp = token
                currentOp.equals("-") -> currentNumber -= token.toInt()
                currentOp.equals("/") -> {
                    if(token.toInt() != 0) {
                        currentNumber /= token.toInt()
                    } else {
                        currentNumber = 0
                    }
                }
                currentOp.equals("*") -> currentNumber *= token.toInt()
                else -> {
                    if(token.isNotEmpty()) {
                        currentNumber += token.toInt()
                    }
                }

            }
        }

        return currentNumber
    }
}