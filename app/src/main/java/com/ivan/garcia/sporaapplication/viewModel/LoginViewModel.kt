package com.ivan.garcia.sporaapplication.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivan.garcia.sporaapplication.models.LoginResponse
import com.ivan.garcia.sporaapplication.repository.LoginRepo
import kotlinx.coroutines.launch

class LoginViewModel(private val loginRepo: LoginRepo) : ViewModel() {

    private val _isLoading by lazy { MutableLiveData<Boolean>() }
    val isLoading: LiveData<Boolean> by lazy { _isLoading }

    private val _error by lazy { MutableLiveData<String>() }
    val error: LiveData<String> by lazy { _error }

    private val _responseData by lazy { MutableLiveData<LoginResponse>() }
    val responseData by lazy { _responseData }

    fun onLoginClick(username: String, pass: String) {
        setLoadingState(true)

        viewModelScope.launch {
            loginRepo.validateLogin(username, pass).let { loginResponse ->
                onResponseReceived(loginResponse)
            }
        }
    }

    private fun onResponseReceived(responseData: LoginResponse?) {
        if (responseData != null) {
            _responseData.postValue(responseData)
            _error.postValue("")
        } else {
            _error.postValue("Something went wrong")
        }
        setLoadingState(false)
    }

    fun setLoadingState(state: Boolean) {
        _isLoading.postValue(state)
    }

}