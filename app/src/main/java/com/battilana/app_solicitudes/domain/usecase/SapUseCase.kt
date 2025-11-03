package com.battilana.app_solicitudes.domain.usecase

import com.battilana.app_solicitudes.data.model.ClientesSapResponse
import com.battilana.app_solicitudes.domain.repository.SapRepository
import javax.inject.Inject

class SapUseCase @Inject constructor(
    private val repository: SapRepository
) {
    suspend fun listarClientesPorVendedor(idVendedor: Int): List<ClientesSapResponse>{
        return this.repository.listarClientesPorVendedor(idVendedor)
    }
}