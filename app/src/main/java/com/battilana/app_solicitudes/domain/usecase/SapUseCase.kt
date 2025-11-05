package com.battilana.app_solicitudes.domain.usecase

import com.battilana.app_solicitudes.data.model.ArticulosResponse
import com.battilana.app_solicitudes.data.model.ClientesSapResponse
import com.battilana.app_solicitudes.data.model.StockAlmacenResponse
import com.battilana.app_solicitudes.domain.repository.SapRepository
import javax.inject.Inject

class SapUseCase @Inject constructor(
    private val repository: SapRepository
) {
    suspend fun listarClientesPorVendedor(idVendedor: Int): List<ClientesSapResponse>{
        return this.repository.listarClientesPorVendedor(idVendedor)
    }

    suspend fun listarArticulosPorAlmacen(idAlmacen: String): List<ArticulosResponse>{
        return this.repository.listarArticulosPorAlmacen(idAlmacen)
    }

    suspend fun stockPorArticuloYAlmacen(idArticulo: String, idAlmacen: String): StockAlmacenResponse{
        return this.repository.stockPorArticuloYAlmacen(idArticulo, idAlmacen)
    }
}