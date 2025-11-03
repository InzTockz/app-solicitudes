package com.battilana.app_solicitudes.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UsuarioRequest(
    val names:String,
    val subnames:String,
    val email:String,
    val username:String,
    val password:String
)