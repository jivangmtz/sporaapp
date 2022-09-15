package com.ivan.garcia.sporaapplication

import java.util.regex.Matcher
import java.util.regex.Pattern

object Login {
    private const val EMAIL_PATTERN =
        "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"

    private const val PASSWORD_PATTERN =
        "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#*$%^&+=])(?=\\S+$).{4,}$"

    private val emailPattern: Pattern = Pattern.compile(EMAIL_PATTERN)
    private val passwordPattern: Pattern = Pattern.compile(PASSWORD_PATTERN)
    private lateinit var matcher: Matcher

    fun validateLoginInput(
        email: String,
        password: String,
    ): Boolean {
        if (email.isEmpty() || password.isEmpty()) {
            return false
        }
        if (!isAValidEmail(email)) {
            return false
        }
        if (!isAValidPassword(password)) {
            return false
        }

        return true
    }

    private fun isAValidEmail(email: String): Boolean {
        matcher = emailPattern.matcher(email)
        return matcher.matches()
    }

    private fun isAValidPassword(password: String): Boolean {
        matcher = passwordPattern.matcher(password)
        return matcher.matches()
    }

}