package com.ivan.garcia.sporaapplication

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class LoginTest {

    private lateinit var email: String
    private lateinit var password: String

    @Test
    fun `on empty email and password returns false`() {
        email = ""
        password = ""

        val result = Login.validateLoginInput(email, password)

        assertThat(result).isFalse()
    }

    @Test
    fun `on empty email returns false`() {
        email = ""
        password = "1234"

        val result = Login.validateLoginInput(email, password)

        assertThat(result).isFalse()
    }

    @Test
    fun `on empty password returns false`() {
        email = "mail@mail.com"
        password = ""

        val result = Login.validateLoginInput(email, password)

        assertThat(result).isFalse()
    }

    @Test
    fun `on invalid email format returns false`() {
        email = "mail@mail"
        password = "12345"

        val result = Login.validateLoginInput(email, password)

        assertThat(result).isFalse()
    }

    @Test
    fun `on invalid password format returns false`() {
        email = "mail@mail.com"
        password = "123567"

        val result = Login.validateLoginInput(email, password)

        assertThat(result).isFalse()
    }

    @Test
    fun `on valid email and password formats returns true`() {
        email = "mail@mail.com"
        password = "123Abc*"

        val result = Login.validateLoginInput(email, password)

        assertThat(result).isTrue()
    }

}