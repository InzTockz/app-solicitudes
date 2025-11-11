package com.battilana.app_solicitudes.data.model

import kotlinx.serialization.Serializable

@Serializable
data class DraftResponse(
    val DocEntry: String,
    val DocNum:String,
    val DocObjectCode :String,
    val CardCode: String,
    val CardName:String,
    val DocDate:String,
    val SalesPersonCode:String,
    val DocTotal:String,
    val Comments:String,
    val DocumentLines: List<DraftDocumentLineRequest>
)
