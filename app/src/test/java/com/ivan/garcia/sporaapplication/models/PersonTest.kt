package com.ivan.garcia.sporaapplication.models

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class PersonTest {

    private val name = "Juan"
    private val lastName = "Lopez"
    private val age = 50
    private lateinit var persona: Person

    @Before
    fun setUp() {
        persona = Person(name, lastName, age)
    }

    @Test
    fun verifyThatReturnsCorrectFullName() {
        //Arrange
        val fullNameExpected = "$name $lastName"

        //Act
        val result = persona.toString()

        //Assert
        assertEquals(fullNameExpected, result)
    }
}