package com.karlosarr.calculadora

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCenter.start(
            application, "8e85c38f-a111-4915-accc-5d5bebb6ab70",
            Analytics::class.java, Crashes::class.java
        )
        setContentView(R.layout.activity_main)
        updateDisplay("")
    }
    val operationList: MutableList<String> = arrayListOf()
    val numberCache: MutableList<String> = arrayListOf()
    //I couldn't find mkString.. so I improvised
    fun makeString(list: List<String>, joiner: String = "") : String {

        if (list.isEmpty()) return ""
        return list.reduce { r, s -> r + joiner + s }
    }

    fun clearCache() {
        numberCache.clear()
        operationList.clear()
    }

    fun updateDisplay(mainDisplayString: String){

        val fullCalculationString = makeString(operationList, " ")
        var fullCalculationTextView = findViewById(R.id.fullCalculationText) as TextView
        fullCalculationTextView.text = fullCalculationString

        val mainTextView = findViewById(R.id.textView) as TextView
        mainTextView.text = mainDisplayString
    }

    fun clearClick(view: View) {
        clearCache()
        updateDisplay("");
    }

    fun equalsClick(view: View) {
        operationList.add(makeString(numberCache))
        numberCache.clear()
        val calculator = StringCalculator()
        val answer = calculator.calculate(operationList)

        updateDisplay("=$answer")
        clearCache()
    }

    fun negateNumber(view: View){
        if (numberCache.isNotEmpty()) {
            if (numberCache.first().equals("-")) {
                numberCache.removeAt(0)
            } else numberCache.add(0, "-")
        } else numberCache.add("-")

        val numberString = makeString(numberCache)
        updateDisplay(numberString)
    }

    fun buttonClick(view: View) {

        val button = view as Button

        if (numberCache.isEmpty()) return

        operationList.add(makeString(numberCache))
        numberCache.clear()
        operationList.add(button.text.toString())

        updateDisplay(button.text.toString())
    }

    fun numberClick(view: View) {
        val button = view as Button
        val numberString = button.text;

        numberCache.add(numberString.toString())
        val text = makeString(numberCache);
        updateDisplay(text)
    }
}