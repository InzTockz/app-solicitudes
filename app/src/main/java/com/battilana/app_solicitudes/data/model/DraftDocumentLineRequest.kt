package com.battilana.app_solicitudes.data.model

import kotlinx.serialization.Serializable

@Serializable
data class DraftDocumentLineRequest(
    val ItemCode: String,
    val Quantity: String,
    val TaxCode: String
)
