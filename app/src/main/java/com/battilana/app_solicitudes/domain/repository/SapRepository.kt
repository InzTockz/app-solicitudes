package com.battilana.app_solicitudes.domain.repository

import com.battilana.app_solicitudes.data.model.ArticulosResponse
import com.battilana.app_solicitudes.data.model.ClientesSapResponse
import com.battilana.app_solicitudes.data.model.DraftRequest
import com.battilana.app_solicitudes.data.model.DraftResponse
import com.battilana.app_solicitudes.data.model.DraftSapResponse
import com.battilana.app_solicitudes.data.model.StockAlmacenResponse
import retrofit2.Response

interface SapRepository {

    suspend fun listarClientesPorVendedor(idVendedor: Int) : List<ClientesSapResponse>
    suspend fun listarClientesPorVendedorYCardName(idVendedor: Int, cardName: String): List<ClientesSapResponse>

    suspend fun listarArticulosPorAlmacen(idAlmacen: String, nombre: String): List<ArticulosResponse>

    suspend fun stockPorArticuloYAlmacen(idArticulo:String, idAlmacen:String) : StockAlmacenResponse
    //DRAFT UPDATE 2.0V
    suspend fun agregarDraft(draftRequest: DraftRequest, idUsuarioSap: Int) : Response<DraftResponse>

    suspend fun listarDraftsPorVendedorYFecha(idVendedor: Int, fechaInicio: String, fechaFin: String): List<DraftSapResponse>
}