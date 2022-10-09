package com.app.hellokmp

import kotlin.test.Test
import kotlin.test.assertTrue

class calculatorTest {

    @Test
    fun testSum() {
        val sum = Calculator.sum(10, 20)
        assertTrue(sum == 30, "Check sum result")
    }
}