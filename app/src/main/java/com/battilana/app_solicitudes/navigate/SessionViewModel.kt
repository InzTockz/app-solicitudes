package com.battilana.app_solicitudes.navigate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.battilana.app_solicitudes.data.local.UserPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(private val userPreferences: UserPreferences): ViewModel() {

    val userSession = userPreferences.userSession

    fun logout(onLoggetdOut:() -> Unit){
        viewModelScope.launch {
            userPreferences.clearSession()
            onLoggetdOut()
        }
    }
}