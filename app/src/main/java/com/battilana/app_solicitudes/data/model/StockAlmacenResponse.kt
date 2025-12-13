package com.battilana.app_solicitudes.data.model

import kotlinx.serialization.Serializable

@Serializable
data class StockAlmacenResponse(
    val stock: Double,
    val comprometido: Double,
    val stockTotal: Double,
    val unidadMedida: String,
    val codigoArticulo: String,
    //val codigoAlmacen: String
)
