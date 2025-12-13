package com.battilana.app_solicitudes.data.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse (
    val idUsuario: Long,
    val codigo: Int?,
    val token:String,
    val status:String
)