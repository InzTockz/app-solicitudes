package com.battilana.app_solicitudes.data.repository

import com.battilana.app_solicitudes.data.model.ArticulosResponse
import com.battilana.app_solicitudes.data.model.ClientesSapResponse
import com.battilana.app_solicitudes.data.model.DraftRequest
import com.battilana.app_solicitudes.data.model.DraftResponse
import com.battilana.app_solicitudes.data.model.DraftSapResponse
import com.battilana.app_solicitudes.data.model.StockAlmacenResponse
import com.battilana.app_solicitudes.data.remote.ApiService
import com.battilana.app_solicitudes.domain.repository.SapRepository
import javax.inject.Inject

class SapRepositoryImpl @Inject constructor(
    private val api: ApiService
) : SapRepository {

    override suspend fun listarClientesPorVendedor(idVendedor: Int): List<ClientesSapResponse> {
        return this.api.listarClientesPorVendedor(idVendedor)
    }

    override suspend fun listarArticulosPorAlmacen(idAlmacen: String): List<ArticulosResponse> {
        return this.api.listarArticulosPorAlmacen(idAlmacen)
    }

    override suspend fun stockPorArticuloYAlmacen(
        idArticulo: String,
        idAlmacen: String
    ): StockAlmacenResponse {
        return this.api.stockPorArticuloYAlmacen(idArticulo, idAlmacen)
    }

    override suspend fun agregarDraft(
        draftRequest: DraftRequest,
        idUsuarioSap: Int
    ): DraftResponse {
        return this.api.agregarDraft(draftRequest, idUsuarioSap)
    }

    override suspend fun listarDraftsPorVendedorYFecha(
        idVendedor: Int,
        fechaInicio: String,
        fechaFin: String
    ): List<DraftSapResponse> {
        return this.api.listarDraftsPorVendedorYFecha(idVendedor, fechaInicio, fechaFin)
    }
}