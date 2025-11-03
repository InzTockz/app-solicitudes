package com.battilana.app_solicitudes.data.repository

import com.battilana.app_solicitudes.data.model.UsuarioSapResponse
import com.battilana.app_solicitudes.data.remote.ApiService
import com.battilana.app_solicitudes.domain.repository.UsuarioSapRepository
import javax.inject.Inject

class UsuarioSapRepositoryImpl @Inject constructor(
    private val api: ApiService
) : UsuarioSapRepository {

    override suspend fun listarUsuarioSap(): List<UsuarioSapResponse> {
        return api.listarUsuarioSap()
    }
}