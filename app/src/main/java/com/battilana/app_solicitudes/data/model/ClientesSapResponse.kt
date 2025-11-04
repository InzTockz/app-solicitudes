package com.battilana.app_solicitudes.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ClientesSapResponse(
    val cardCode:String,
    val cardName:String,
    val email:String?,
    val licTradNum:String
)