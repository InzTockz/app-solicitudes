package com.battilana.app_solicitudes.data.repository

import com.battilana.app_solicitudes.data.model.UsuarioRequest
import com.battilana.app_solicitudes.data.model.UsuarioResponse
import com.battilana.app_solicitudes.data.remote.ApiService
import com.battilana.app_solicitudes.domain.repository.UserRepository
import javax.inject.Inject

class UsuarioRepositoryImpl @Inject constructor(private val api: ApiService) : UserRepository {
    override suspend fun getUsuarioById(idUsuario: Long): UsuarioResponse{
        return api.buscarUsuarioPorId(idUsuario)
    }

    override suspend fun createUsuario(usuarioRequest: UsuarioRequest): UsuarioResponse {
        return api.createUsuario(usuarioRequest)
    }

    override suspend fun updateUsuario(
        idUsuario: Long,
        usuarioRequest: UsuarioRequest
    ): UsuarioResponse {
        return api.updateUsuario(idUsuario, usuarioRequest)
    }

    override suspend fun deleteUsuario(idUsuario: Long): Void {
        return api.deleteUsuario(idUsuario)
    }
}