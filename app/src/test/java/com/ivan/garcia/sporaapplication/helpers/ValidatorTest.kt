package com.ivan.garcia.sporaapplication.helpers

import org.junit.Assert.*
import org.junit.Test

class ValidatorTest {
    @Test
    fun assertTrueWhenANumberGreaterThanZeroIsPositive() {
        val result = Validator.isPositiveNumber(5)
        assertTrue(result)
    }

    @Test
    fun assertFalseWhenANumberSmallerThanZeroIsNegative() {
        val result = Validator.isPositiveNumber(-20)
        assertFalse(result)
    }

    @Test
    fun withACorrectEmailIsAValidEmailReturnsTrue() {
        val result = Validator.isAValidEmail("myemail@email.com")
        assertTrue(result)
    }

    @Test
    fun withAIncorrectEmailIsAValidEmailReturnsFalse() {
        val result = Validator.isAValidEmail("myemail@email")
        assertFalse(result)
    }
}