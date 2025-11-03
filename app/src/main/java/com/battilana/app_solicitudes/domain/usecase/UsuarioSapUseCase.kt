package com.battilana.app_solicitudes.domain.usecase

import com.battilana.app_solicitudes.data.model.UsuarioSapResponse
import com.battilana.app_solicitudes.data.repository.UsuarioSapRepositoryImpl
import com.battilana.app_solicitudes.domain.repository.UsuarioSapRepository
import javax.inject.Inject

class UsuarioSapUseCase @Inject constructor(
    private val repository: UsuarioSapRepository
) {

    suspend fun listarUsuarioSap(): List<UsuarioSapResponse>{
        return repository.listarUsuarioSap()
    }
}