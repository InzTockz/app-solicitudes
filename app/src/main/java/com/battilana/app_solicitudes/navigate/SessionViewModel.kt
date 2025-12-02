package com.battilana.app_solicitudes.navigate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.battilana.app_solicitudes.data.local.UserPreferences
import com.battilana.app_solicitudes.data.model.UserSession
import com.battilana.app_solicitudes.data.model.isExpired
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
//class SessionViewModel @Inject constructor(private val userPreferences: UserPreferences): ViewModel() {
//
//    val userSession = userPreferences.userSession
//
//    fun logout(onLoggetdOut:() -> Unit){
//        viewModelScope.launch {
//            userPreferences.clearSession()
//            onLoggetdOut()
//        }
//    }
//}

@HiltViewModel
class SessionViewModel @Inject constructor(private val userPreferences: UserPreferences) :
    ViewModel() {

    val userSession = userPreferences.userSession

    private val _sessionState = MutableStateFlow(SessionState.LOADING)
    val sessionState: StateFlow<SessionState> = _sessionState

    init {
        observeSession()
    }

    private fun observeSession(){
        viewModelScope.launch {
            userPreferences.userSession.collect { session: UserSession? ->
                if(session == null){
                    _sessionState.value = SessionState.UNAUTHENTICATED
                } else if (session.isExpired()){
                    userPreferences.clearSession()
                    _sessionState.value = SessionState.UNAUTHENTICATED
                } else {
                    _sessionState.value = SessionState.AUTHENTICATED
                }
            }
        }
    }

    fun logout(onLoggetdOut: () -> Unit) {
        viewModelScope.launch {
            userPreferences.clearSession()
            onLoggetdOut()
        }
    }

    fun refreshSessionState(){
        viewModelScope.launch {
            val session = userPreferences.userSession.first()
            if (session == null || session.isExpired()){
                userPreferences.clearSession()
                _sessionState.value = SessionState.UNAUTHENTICATED
            } else {
                _sessionState.value = SessionState.AUTHENTICATED
            }
        }
    }
}