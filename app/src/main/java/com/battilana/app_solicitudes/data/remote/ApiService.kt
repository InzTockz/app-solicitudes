package com.battilana.app_solicitudes.data.remote

import com.battilana.app_solicitudes.data.model.LoginRequest
import com.battilana.app_solicitudes.data.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    //Llamamos al endpoint en el backend de SpringBoot
    @POST(value = "usuario/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}