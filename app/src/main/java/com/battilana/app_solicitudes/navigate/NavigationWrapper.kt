package com.battilana.app_solicitudes.navigate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.battilana.app_solicitudes.ui.screens.login.LoginScreen
import com.battilana.app_solicitudes.ui.screens.navbar.NavigationBarScreen

@Composable
fun NavigationWrapper() {

    val navOptions = rememberNavController()
    val sessionViewModel = hiltViewModel<SessionViewModel>()
    val sessionState by sessionViewModel.sessionState.collectAsState()
//    val session by sessionViewModel.userSession.collectAsState(initial = null)

//    val startDestination = if(session == null) Login else NavigationBar

    NavHost(navController = navOptions, startDestination = Login) {
        composable<Login> {
            LoginScreen(
                navigation = {
                    navOptions.navigate(NavigationBar) {
                        popUpTo(Login) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
        composable<NavigationBar> {
            NavigationBarScreen(
                onLogout = {
                    sessionViewModel.logout(onLoggetdOut = {

                    })
//                    sessionViewModel.logout {
//                        navOptions.navigate(Login) {
//                            popUpTo(NavigationBar) { inclusive = true }
//                            launchSingleTop = true
//                        }
//                    }
                }
            )
        }
    }

    LaunchedEffect(sessionState) {
        when (sessionState) {
            SessionState.UNAUTHENTICATED -> {
                navOptions.navigate(Login) {
                    popUpTo(Login) { inclusive = true }
                    launchSingleTop = true
                }
            }

            SessionState.AUTHENTICATED -> {
                navOptions.navigate(NavigationBar) {
                    popUpTo(Login) { inclusive = true }
                    launchSingleTop = true
                }
            }

            SessionState.LOADING -> {

            }
        }
    }

    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                // La pantalla vuelve al primer plano (desde segundo plano)
                sessionViewModel.refreshSessionState()
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}