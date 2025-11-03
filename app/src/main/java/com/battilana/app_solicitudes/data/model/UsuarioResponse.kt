package com.battilana.app_solicitudes.data.model

import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class UsuarioResponse(
    val idUsuario: Long,
    val names: String,
    val subnames: String,
    val email: String,
    val createAt: String,
    val status: Boolean,
    val username:String,
    val password:String,
    val roles: Roles
    )
