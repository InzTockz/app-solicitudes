package com.battilana.app_solicitudes.domain.repository

import com.battilana.app_solicitudes.data.model.UsuarioSapResponse

interface UsuarioSapRepository {

    suspend fun listarUsuarioSap(): List<UsuarioSapResponse>
}