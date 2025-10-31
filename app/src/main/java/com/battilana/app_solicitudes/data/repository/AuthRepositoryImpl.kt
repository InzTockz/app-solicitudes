package com.battilana.app_solicitudes.data.repository

import com.battilana.app_solicitudes.data.model.LoginRequest
import com.battilana.app_solicitudes.data.model.LoginResponse
import com.battilana.app_solicitudes.data.remote.ApiService
import com.battilana.app_solicitudes.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val api: ApiService) : AuthRepository {

    override suspend fun login(username: String, password: String) : LoginResponse{
        return api.login(LoginRequest(username, password))
    }
}