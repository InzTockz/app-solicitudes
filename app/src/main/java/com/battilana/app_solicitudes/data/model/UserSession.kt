package com.battilana.app_solicitudes.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserSession (
    val idUsuario: Long,
    val names:String,
    val subnames:String,
    val token:String,
    val status:String
)