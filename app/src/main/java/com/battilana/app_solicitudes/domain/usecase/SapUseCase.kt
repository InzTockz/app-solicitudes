package com.battilana.app_solicitudes.domain.usecase

import com.battilana.app_solicitudes.data.model.ArticulosResponse
import com.battilana.app_solicitudes.data.model.ClientesSapResponse
import com.battilana.app_solicitudes.data.model.DraftRequest
import com.battilana.app_solicitudes.data.model.DraftResponse
import com.battilana.app_solicitudes.data.model.DraftSapResponse
import com.battilana.app_solicitudes.data.model.StockAlmacenResponse
import com.battilana.app_solicitudes.domain.repository.SapRepository
import retrofit2.Response
import javax.inject.Inject

class SapUseCase @Inject constructor(
    private val repository: SapRepository
) {
    suspend fun listarClientesPorVendedor(idVendedor: Int): List<ClientesSapResponse>{
        return this.repository.listarClientesPorVendedor(idVendedor)
    }

    suspend fun listarClientesPorVendedorYCardName(idVendedor: Int, cardName: String): List<ClientesSapResponse>{
        return this.repository.listarClientesPorVendedorYCardName(idVendedor, cardName)
    }

    suspend fun listarArticulosPorAlmacen(idAlmacen: String, nombre: String): List<ArticulosResponse>{
        return this.repository.listarArticulosPorAlmacen(idAlmacen, nombre)
    }

    suspend fun stockPorArticuloYAlmacen(idArticulo: String, idAlmacen: String): StockAlmacenResponse{
        return this.repository.stockPorArticuloYAlmacen(idArticulo, idAlmacen)
    }

    suspend fun agregarDraft(draftRequest: DraftRequest, idUsuarioSap: Int): Response<DraftResponse> {
        return this.repository.agregarDraft(draftRequest, idUsuarioSap)
    }

    suspend fun listarDraftsPorVendedorYFecha(idVendedor: Int, fechaInicio:String, fechaFin:String): List<DraftSapResponse>{
        return this.repository.listarDraftsPorVendedorYFecha(idVendedor, fechaInicio, fechaFin)
    }
}