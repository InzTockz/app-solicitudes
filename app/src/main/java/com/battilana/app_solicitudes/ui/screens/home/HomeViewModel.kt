package com.battilana.app_solicitudes.ui.screens.home

import androidx.lifecycle.ViewModel
import com.battilana.app_solicitudes.data.local.UserPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val userPreferences: UserPreferences): ViewModel() {

    val userSession = userPreferences.userSession


}