package com.battilana.app_solicitudes.view.screens.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel: ViewModel() {

    private val _uiStateLogin = MutableStateFlow(LoginUiState())
    val uiStateLogin: StateFlow<LoginUiState> = _uiStateLogin

    //PRIMERA FORMA DE HACER UN UPDATE
    fun onShowPasswordChange() {
        _uiStateLogin.update { state ->
            state.copy(viewPassword = !state.viewPassword)
        }
    }

    //SEGUNDA FORMA DE HACER UN UPDATE
    fun onPasswordChange(password: String) {
        _uiStateLogin.update {
            it.copy(password = password)
        }
        onButtonLoginChange()
    }

    fun onUsernameChange(username: String) {
        _uiStateLogin.update { state ->
            state.copy(username = username)
        }
        onButtonLoginChange()
    }

    fun onButtonLoginChange() {
        val isUsernameValid: Boolean = !uiStateLogin.value.username.equals("")
        val isPasswordValid: Boolean = _uiStateLogin.value.password.length >= 8
        val enabledLogin: Boolean = isPasswordValid && isUsernameValid
        _uiStateLogin.update { it.copy(enabledButtonLogin = enabledLogin) }
    }
}

data class LoginUiState(
    val username: String = "",
    val password: String = "",
    val viewPassword: Boolean = false,
    val enabledButtonLogin: Boolean = false
)