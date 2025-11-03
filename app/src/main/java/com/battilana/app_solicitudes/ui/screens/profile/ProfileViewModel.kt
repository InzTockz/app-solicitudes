package com.battilana.app_solicitudes.ui.screens.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.battilana.app_solicitudes.data.local.UserPreferences
import com.battilana.app_solicitudes.data.model.Roles
import com.battilana.app_solicitudes.data.model.UsuarioResponse
import com.battilana.app_solicitudes.domain.usecase.UsuarioUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userPreferences: UserPreferences,
    private val usuarioUseCase: UsuarioUseCase
) : ViewModel() {

    private val _uiStateUsuario = MutableStateFlow<UsuarioResponse?>(null)
    val usuario: StateFlow<UsuarioResponse?> = _uiStateUsuario
//    private val _uiStateUsuario = MutableStateFlow(UsuarioUiState())
//    val usuario: StateFlow<UsuarioUiState> = _uiStateUsuario

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    fun cargarUsuario(){
        viewModelScope.launch {
            try {
                _loading.value = true
                val session = userPreferences.userSession.first()
                val idUsuario = session?.idUsuario ?: return@launch

                val usuario = usuarioUseCase.getUsuarioById(idUsuario)

                _uiStateUsuario.value = usuario
            } catch (e: Exception){
                Log.i("ERROR_FIND_USER", "${e.message}")
            } finally {
                _loading.value = false
            }
        }
    }
}

data class UsuarioUiState(
    val idUsuario: Long = 0,
    val names: String = "",
    val subnames: String = "",
    val email: String = "",
    val createAt: String = "",
    val status: Boolean = false,
    val username:String = "",
    val password:String = "",
    val roles: Roles = Roles.USUARIO
)