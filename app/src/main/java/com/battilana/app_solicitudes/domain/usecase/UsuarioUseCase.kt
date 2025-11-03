package com.battilana.app_solicitudes.domain.usecase

import com.battilana.app_solicitudes.data.model.UsuarioRequest
import com.battilana.app_solicitudes.data.model.UsuarioResponse
import com.battilana.app_solicitudes.data.repository.UsuarioRepositoryImpl
import javax.inject.Inject

class UsuarioUseCase @Inject constructor(
    private val repository: UsuarioRepositoryImpl
) {

    suspend fun getUsuarioById(idUsuario: Long): UsuarioResponse{
        return repository.getUsuarioById(idUsuario)
    }

    suspend fun createUsuario(usuarioRequest: UsuarioRequest): UsuarioResponse{
        return repository.createUsuario(usuarioRequest)
    }

    suspend fun updateUsuario(idUsuario: Long, usuarioRequest: UsuarioRequest): UsuarioResponse{
        return repository.updateUsuario(idUsuario, usuarioRequest)
    }

    suspend fun deleteUsuario(idUsuario: Long): Void{
        return repository.deleteUsuario(idUsuario)
    }
}