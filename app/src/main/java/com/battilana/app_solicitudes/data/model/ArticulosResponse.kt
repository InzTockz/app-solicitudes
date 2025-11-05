package com.battilana.app_solicitudes.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ArticulosResponse(
    val itemCode: String,
    val itemName: String
)
