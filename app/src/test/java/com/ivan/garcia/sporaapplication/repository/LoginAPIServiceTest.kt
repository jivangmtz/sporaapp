package com.ivan.garcia.sporaapplication.repository

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginAPIServiceTest {

    @Mock
    lateinit var mockWebServer: MockWebServer

    @Mock
    lateinit var apiService: LoginAPIService

    lateinit var gson: Gson

    @Before
    fun setup() {
        gson = GsonBuilder().create()
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(LoginAPIService::class.java)
    }

    @Test
    fun validateUserData_return_success() {
        runBlocking {
            val mockResponse = MockResponse()
            mockWebServer.enqueue(
                mockResponse.setBody("{ }")
            )
            val response = apiService.validateUserData("","")
            val request = mockWebServer.takeRequest()

            Assert.assertEquals(request.path, LoginAPIService.VALIDATE_USER_PATH)
            Assert.assertNotNull(response)
        }
    }

    @After
    fun deconstruct() {
        mockWebServer.shutdown()
    }

}