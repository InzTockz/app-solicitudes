package com.battilana.app_solicitudes.data.model

import kotlinx.serialization.Serializable

@Serializable
data class DraftRequest (
    val CardCode: String,
    val DocObjectCode: String,
    val Comments: String,
    val SalesPersonCode: String,
    val DocumentLines: List<DraftDocumentLineRequest>
)