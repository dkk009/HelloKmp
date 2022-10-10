package com.app.hellokmp

class Calculator {
    companion object {
        fun sum(number1: Float, number2: Float) = number1 + number2
        fun subtraction(number1: Float, number2: Float) = number1 - number2
        fun multiplication(number1: Float, number2: Float) = number1 * number2
        @Throws(ArithmeticException::class)
        fun division(number1: Float, number2: Float): Float {
            if(number2 ==0f){
                throw ArithmeticException("Division by 0 is not possible")
            }
            return number1/number2
        }
    }
}