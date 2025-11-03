package com.battilana.app_solicitudes.domain.repository

import com.battilana.app_solicitudes.data.model.ClientesSapResponse

interface SapRepository {

    suspend fun listarClientesPorVendedor(idVendedor: Int) : List<ClientesSapResponse>
}