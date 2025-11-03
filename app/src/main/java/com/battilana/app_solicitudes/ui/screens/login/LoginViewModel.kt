package com.battilana.app_solicitudes.ui.screens.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.battilana.app_solicitudes.data.local.UserPreferences
import com.battilana.app_solicitudes.data.model.UserSession
import com.battilana.app_solicitudes.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val userPreferences: UserPreferences
) : ViewModel() {

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
        _uiStateLogin.update { state ->
            state.copy(password = password)
        }

//        _uiStateLogin.value = _uiStateLogin.value.copy(password = password)
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

    fun login(onSuccess: () -> Unit){
        viewModelScope.launch {
            try {
                //CAMBIAR EL LOADING A TRUE Y GESTIONAR UN TIEMPO DE 1 SEGUNDO PARA EJECUTAR EL SIG. CODIGO
                _uiStateLogin.value = _uiStateLogin.value.copy(isLoading = true)
                delay(1000)

                val response = loginUseCase(_uiStateLogin.value.username, _uiStateLogin.value.password)
                val session = UserSession(
                    response.idUsuario,
                    response.names,
                    response.subnames,
                    response.token,
                    response.status
                )
                userPreferences.saveUserSession(session)

                onSuccess()
            } catch (e: Exception){
                _uiStateLogin.value = _uiStateLogin.value.copy(error = e.message, isLoading = false)
            } finally {
                _uiStateLogin.value = _uiStateLogin.value.copy(isLoading = false)
            }
        }
    }
}

data class LoginUiState(
    val username: String = "fredy",
    val password: String = "mugiwara",
    val viewPassword: Boolean = false,
    val isLoading: Boolean = false,
    val enabledButtonLogin: Boolean = false,
    val error: String? = null
)