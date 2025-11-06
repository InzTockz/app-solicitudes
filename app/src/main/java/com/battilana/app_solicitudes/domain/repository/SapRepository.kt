package com.battilana.app_solicitudes.domain.repository

import com.battilana.app_solicitudes.data.model.ArticulosResponse
import com.battilana.app_solicitudes.data.model.ClientesSapResponse
import com.battilana.app_solicitudes.data.model.DraftRequest
import com.battilana.app_solicitudes.data.model.DraftResponse
import com.battilana.app_solicitudes.data.model.StockAlmacenResponse

interface SapRepository {

    suspend fun listarClientesPorVendedor(idVendedor: Int) : List<ClientesSapResponse>
    suspend fun listarArticulosPorAlmacen(idAlmacen: String): List<ArticulosResponse>
    suspend fun stockPorArticuloYAlmacen(idArticulo:String, idAlmacen:String) : StockAlmacenResponse
    suspend fun agregarDraft(draftRequest: DraftRequest, idUsuarioSap: Int) : DraftResponse
}