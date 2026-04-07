package com.example.myapplication.data.remote

import com.example.myapplication.data.model.AuthRequest
import com.example.myapplication.data.model.LoginResponse
import com.example.myapplication.data.model.MessageResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("health")
    suspend fun health(): Response<String>

    @POST("register")
    suspend fun register(
        @Body request: AuthRequest
    ): Response<MessageResponse>

    @POST("login")
    suspend fun login(
        @Body request: AuthRequest
    ): Response<LoginResponse>
}