package com.battilana.app_solicitudes.navigate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.battilana.app_solicitudes.ui.screens.login.LoginScreen
import com.battilana.app_solicitudes.ui.screens.navbar.NavigationBarScreen

@Composable
fun NavigationWrapper() {

    val navOptions = rememberNavController()
    val sessionViewModel = hiltViewModel<SessionViewModel>()
    val session by sessionViewModel.userSession.collectAsState(initial = null)

    val startDestination = if(session == null) Login else NavigationBar

    NavHost(navController = navOptions, startDestination = startDestination) {
        composable<Login> {
            LoginScreen(
                navigation = { navOptions.navigate(NavigationBar){
                 popUpTo(Login) { inclusive = true}
                }}
            )
        }
        composable<NavigationBar> {
            NavigationBarScreen(
                onLogout = {
                    sessionViewModel.logout {
                        navOptions.navigate(Login){
                            popUpTo(NavigationBar) { inclusive = true}
                            launchSingleTop = true
                        }
                    }
                }
            )
        }
    }
}