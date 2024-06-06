package com.example.tuanphambaitestmaytinh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView
    private var currentNumber = ""
    private var operator = ""
    private var oldNumber = ""
    private var isNewOp = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.resultTextView)
    }

    fun onDigit(view: View) {
        if (isNewOp) {
            resultTextView.text = ""
            isNewOp = false
        }
        currentNumber += (view as Button).text
        resultTextView.text = currentNumber
    }

    fun onOperator(view: View) {
        oldNumber = currentNumber
        operator = (view as Button).text.toString()
        currentNumber = ""
    }

    fun onEqual(view: View) {
        val newNumber = currentNumber
        var result = 0

        when (operator) {
            "+" -> result = oldNumber.toInt() + newNumber.toInt()
            "-" -> result = oldNumber.toInt() - newNumber.toInt()
            "*" -> result = oldNumber.toInt() * newNumber.toInt()
            "/" -> {
                if (newNumber.toInt() != 0) {
                    result = oldNumber.toInt() / newNumber.toInt()

                } else {
                    // Xử lý trường hợp chia cho 0
                    resultTextView.text = "Error"

                    return
                }
            }
        }
        resultTextView.text = result.toString()
        isNewOp = true
    }

    fun onClear(view: View) {
        resultTextView.text = "0"
        currentNumber = ""
        operator = ""
        oldNumber = ""
        isNewOp = true
    }

    fun onDot(view: View) {
        if (!currentNumber.contains(".")) {
            currentNumber += "."
            resultTextView.text = currentNumber
        }
    }

    fun onBackspace(view: View) {
        if (currentNumber.isNotEmpty()) {
            currentNumber = currentNumber.substring(0, currentNumber.length - 1)
            resultTextView.text = if (currentNumber.isEmpty()) "0" else currentNumber
        }

    }
}