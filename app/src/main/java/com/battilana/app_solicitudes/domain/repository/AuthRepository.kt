package com.battilana.app_solicitudes.domain.repository

import com.battilana.app_solicitudes.data.model.LoginResponse

interface AuthRepository {
    suspend fun login(username: String, password: String): LoginResponse
}