package com.battilana.app_solicitudes.domain.repository

import com.battilana.app_solicitudes.data.model.UsuarioRequest
import com.battilana.app_solicitudes.data.model.UsuarioResponse

interface UserRepository {
    suspend fun getUsuarioById(idUsuario: Long): UsuarioResponse
    suspend fun createUsuario(usuarioRequest: UsuarioRequest): UsuarioResponse
    suspend fun updateUsuario(idUsuario: Long, usuarioRequest: UsuarioRequest): UsuarioResponse
    suspend fun deleteUsuario(idUsuario: Long): Void
}