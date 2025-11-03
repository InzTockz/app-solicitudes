package com.battilana.app_solicitudes.domain.usecase

import com.battilana.app_solicitudes.data.model.LoginResponse
import com.battilana.app_solicitudes.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: AuthRepository) {

    suspend operator fun invoke(username:String, password:String) : LoginResponse{
        return repository.login(username, password)
    }
}