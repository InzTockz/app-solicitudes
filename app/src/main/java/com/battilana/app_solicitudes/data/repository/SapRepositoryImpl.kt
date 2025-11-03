package com.battilana.app_solicitudes.data.repository

import com.battilana.app_solicitudes.data.model.ClientesSapResponse
import com.battilana.app_solicitudes.data.remote.ApiService
import com.battilana.app_solicitudes.domain.repository.SapRepository
import javax.inject.Inject

class SapRepositoryImpl @Inject constructor(
    private val api: ApiService
) : SapRepository {

    override suspend fun listarClientesPorVendedor(idVendedor: Int): List<ClientesSapResponse> {
        return this.api.listarClientesPorVendedor(idVendedor)
    }
}