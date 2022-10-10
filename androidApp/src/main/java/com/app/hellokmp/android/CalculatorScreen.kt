package com.app.hellokmp.android

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.app.hellokmp.Calculator


private const val DEFAULT_TEXT = "Calculator"

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CalculatorScreen() {
    val calButtonItems = arrayOf(
        arrayOf("C", "+/-", "%", "/"),
        arrayOf("7", "8", "9", "X"),
        arrayOf("4", "5", "6", "-"),
        arrayOf("1", "2", "3", "+"),
        arrayOf("0", ".", "=")
    )
    Scaffold() {
        var result by remember {
            mutableStateOf(DEFAULT_TEXT)
        }
        var numberOne by remember {
            mutableStateOf("")
        }
        var numberTwo by remember {
            mutableStateOf("")
        }
        var operator by remember {
            mutableStateOf("")
        }
        Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Bottom) {
            Text(
                text = result,
                style = MaterialTheme.typography.h3,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 10.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            calButtonItems.forEach { row ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    row.forEach { item ->
                        Button(
                            onClick = {
                                when (item) {
                                    "C" -> {
                                        result = ""
                                    }
                                    "X", "-", "+", "=", "/" -> {
                                        when (item) {
                                            "=" -> {
                                                numberTwo = result
                                                val runningNumberOne = numberOne.toFloat()
                                                val runningNumberTwo = numberTwo.toFloat()
                                                when (operator) {
                                                    "+" -> result = Calculator.sum(
                                                        runningNumberOne,
                                                        runningNumberTwo
                                                    ).toString()
                                                    "-" -> result = Calculator.subtraction(
                                                        runningNumberOne,
                                                        runningNumberTwo
                                                    ).toString()
                                                    "X" -> result = Calculator.multiplication(
                                                        runningNumberOne,
                                                        runningNumberTwo
                                                    ).toString()
                                                    "/" -> result = try {
                                                        Calculator.division(
                                                            runningNumberOne,
                                                            runningNumberTwo
                                                        ).toString()
                                                    } catch (exc: java.lang.ArithmeticException) {
                                                        exc.localizedMessage
                                                    }
                                                }
                                                operator = item
                                            }
                                            else -> {
                                                numberOne = result
                                                result = ""
                                                operator = item
                                            }
                                        }
                                    }
                                    "+/-", "%" -> {

                                    }
                                    "." -> {

                                    }
                                    else -> {
                                        if (result == DEFAULT_TEXT || operator == "=") {
                                            result = ""
                                        }
                                        result += item
                                    }
                                }
                            }, modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .padding(horizontal = 5.dp, vertical = 5.dp)
                                .height(72.dp)
                        ) {
                            Text(text = item, style = MaterialTheme.typography.h4)
                        }
                    }

                }
            }
        }
    }
}

fun handleCalcButtonClick(item: String) {

}
