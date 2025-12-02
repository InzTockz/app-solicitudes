package com.battilana.app_solicitudes.data.remote

import com.battilana.app_solicitudes.data.model.ArticulosResponse
import com.battilana.app_solicitudes.data.model.ClientesSapResponse
import com.battilana.app_solicitudes.data.model.DraftRequest
import com.battilana.app_solicitudes.data.model.DraftResponse
import com.battilana.app_solicitudes.data.model.DraftSapResponse
import com.battilana.app_solicitudes.data.model.LoginRequest
import com.battilana.app_solicitudes.data.model.LoginResponse
import com.battilana.app_solicitudes.data.model.StockAlmacenResponse
import com.battilana.app_solicitudes.data.model.UsuarioRequest
import com.battilana.app_solicitudes.data.model.UsuarioResponse
import com.battilana.app_solicitudes.data.model.UsuarioSapResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import java.time.LocalDate

interface ApiService {

    // ---------- LOGIN ----------
    //Llamamos al endpoint en el backend de SpringBoot
    @POST(value = "usuario/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    // ---------- USUARIO ----------
    @GET(value = "usuario/find/{idUsuario}")
    suspend fun buscarUsuarioPorId(
        @Path("idUsuario") idUsuario: Long
    ): UsuarioResponse

    @POST("")
    suspend fun createUsuario(@Body usuarioRequest: UsuarioRequest): UsuarioResponse

    @PUT("")
    suspend fun updateUsuario(
        @Path("idUsuario") idUsuario: Long,
        @Body usuarioRequest: UsuarioRequest
    ): UsuarioResponse

    @DELETE("")
    suspend fun deleteUsuario(@Path("idUsuario") idUsuario: Long): Void

    // ---------- USUARIO SAP ----------
    @GET(value = "usuario-sap/listar")
    suspend fun listarUsuarioSap(): List<UsuarioSapResponse>

    // ---------- CONSULTAS SAP ----------
    @GET(value = "sap/listado/clientes/{idVendedor}")
    suspend fun listarClientesPorVendedor(@Path("idVendedor") idVendedor: Int): List<ClientesSapResponse>

    @GET(value = "sap/listar-clientes/vendedor/{idVendedor}")
    suspend fun listarClientesPorVendedorYCardName(
        @Path("idVendedor") idVendedor: Int,
        @Query("cardName") cardName: String
    ): List<ClientesSapResponse>

    @GET(value = "sap/listar-articulos/{idAlmacen}")
    suspend fun listarArticulosPorAlmacen(
        @Path("idAlmacen") idAlmacen: String,
        @Query("nombre") nombre: String
    ): List<ArticulosResponse>

    @GET(value = "sap/stock/articulo/{idArticulo}/almacen/{idAlmacen}")
    suspend fun stockPorArticuloYAlmacen(
        @Path("idArticulo") idArticulo: String,
        @Path("idAlmacen") idAlmacen: String
    ): StockAlmacenResponse

    @POST(value = "sap/agregar/user-sap/{idUsuarioSap}")
    suspend fun agregarDraft(
        @Body draftRequest: DraftRequest,
        @Path("idUsuarioSap") idUsuarioSap: Int
    ): Response<DraftResponse>

    @GET(value = "sap/listar-draft/{idVendedor}")
    suspend fun listarDraftsPorVendedorYFecha(
        @Path("idVendedor") idVendedor: Int,
        @Query("fechaInicio") fechaInicio: String,
        @Query("fechaFin") fechaFin: String
    ): List<DraftSapResponse>
}