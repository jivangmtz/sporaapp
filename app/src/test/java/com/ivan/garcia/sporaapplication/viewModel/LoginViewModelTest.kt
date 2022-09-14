package com.ivan.garcia.sporaapplication.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ivan.garcia.sporaapplication.models.LoginResponse
import com.ivan.garcia.sporaapplication.repository.LoginRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

//4
@ExperimentalCoroutinesApi //corrutina
@RunWith(JUnit4::class)
class LoginViewModelTest {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var loginRepo: LoginRepo
    private lateinit var email: String
    private lateinit var password: String

    //1
    //androidx.arch.core.executor.testing;
    @get:Rule //regla para testear los live data / truquear el dispatcher
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        //2
        //Dispatcher se encarga de manejar los hilos que usaran nuestras corrutinas
        Dispatchers.setMain(Dispatchers.Unconfined)

        loginRepo = Mockito.mock(LoginRepo::class.java)
        loginViewModel = LoginViewModel(loginRepo)

        email = "mail@mail.com"
        password = "MyPassword"
    }

    @Test
    fun verifyThatIsLoadingOnSetLoadingStateToTrue() {
        loginViewModel.setLoadingState(true)
        Assert.assertTrue(loginViewModel.isLoading.value!!)
    }

    @Test
    fun verifyThatIsNotLoadingOnSetLoadingStateToFalse() {
        loginViewModel.setLoadingState(false)
        Assert.assertFalse(loginViewModel.isLoading.value!!)
    }

    @Test
    fun verifyFailedStateOnNullResponseAtValidateLogin() {
        //Given
        Mockito.`when`(loginRepo.validateLogin(email, password)).thenReturn(null)

        //When
        loginViewModel.onLoginClick(email, password)

        //Then
        Assert.assertNotNull(loginViewModel.error.value)
        Assert.assertEquals(loginViewModel.error.value, "Something went wrong")
        Assert.assertFalse(loginViewModel.isLoading.value!!)
    }

    @Test
    fun verifySuccessStateOnSuccessLoginResponseAtValidateLogin() {
        Mockito.`when`(loginRepo.validateLogin(email, password)).thenReturn(LoginResponse())

        loginViewModel.onLoginClick(email, password)

        Assert.assertNotNull(loginViewModel.responseData.value)
        Assert.assertTrue(loginViewModel.error.value.isNullOrBlank())
        Assert.assertEquals(loginViewModel.error.value, "")
        Assert.assertFalse(loginViewModel.isLoading.value!!)
    }

    @After
    fun tearDown() {
        //3
        Dispatchers.resetMain()
    }
}