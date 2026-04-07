package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.repository.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val repo = AuthRepository()

    var isLoading = false
    var error: String? = null

    fun register(
        nickname: String,
        password: String,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            isLoading = true
            val result = repo.register(nickname, password)
            isLoading = false

            result.onSuccess {
                onSuccess()
            }.onFailure {
                error = it.message
            }
        }
    }

    fun login(
        nickname: String,
        password: String,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            isLoading = true
            val result = repo.login(nickname, password)
            isLoading = false

            result.onSuccess {
                onSuccess()
            }.onFailure {
                error = it.message
            }
        }
    }
}