package com.ivan.garcia.sporaapplication.repository

import com.ivan.garcia.sporaapplication.models.LoginResponse

interface LoginRepo {
    fun validateLogin(username: String, pass: String): LoginResponse?
}