package com.ivan.garcia.sporaapplication.repository

import com.ivan.garcia.sporaapplication.models.LoginResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

class LoginRepoTest {
    @Mock
    lateinit var loginRepo: LoginRepo

    private lateinit var email: String
    private lateinit var password: String

    @Before
    fun setUp() {
        loginRepo = Mockito.mock(LoginRepo::class.java)

        email = "mail@mail.com"
        password = "MyPassword"
    }

    @Test
    fun verifyThatResponseIsALoginResponseOnValidateLoginSuccess() {
        runBlocking {
            Mockito.`when`(loginRepo.validateLogin(email, password)).thenReturn(
                LoginResponse()
            )
        }

        runBlocking {
            Assert.assertEquals(
                loginRepo.validateLogin(email, password),
                LoginResponse()
            )
        }
    }

    @Test
    fun verifyThatResponseIsNullOnValidateLoginError() {
        runBlocking {
            Mockito.`when`(loginRepo.validateLogin(email, password)).thenReturn(
                null
            )
        }

        runBlocking {
            Assert.assertEquals(loginRepo.validateLogin(email, password), null)
        }
    }
}