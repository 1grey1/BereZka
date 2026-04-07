package com.example.myapplication.data.repository

import com.example.myapplication.data.model.AuthRequest
import com.example.myapplication.data.remote.RetrofitClient

class AuthRepository {

    private val api = RetrofitClient.api

    suspend fun register(nickname: String, password: String): Result<String> {
        return try {
            val response = api.register(AuthRequest(nickname, password))
            if (response.isSuccessful) {
                Result.success(response.body()?.message ?: "OK")
            } else {
                Result.failure(Exception(response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun login(nickname: String, password: String): Result<String> {
        return try {
            val response = api.login(AuthRequest(nickname, password))
            if (response.isSuccessful) {
                Result.success(response.body()?.message ?: "OK")
            } else {
                Result.failure(Exception(response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}