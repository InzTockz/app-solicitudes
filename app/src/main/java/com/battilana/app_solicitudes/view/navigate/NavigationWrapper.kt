package com.battilana.app_solicitudes.view.navigate

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.battilana.app_solicitudes.view.screens.home.HomeScreen
import com.battilana.app_solicitudes.view.screens.login.LoginScreen
import com.battilana.app_solicitudes.view.screens.navbar.NavigationBarScreen

@Composable
fun NavigationWrapper() {

    val navOptions = rememberNavController()

    NavHost(navController = navOptions, startDestination = Login) {
        composable<Login> {
            LoginScreen(
                navigation = { navOptions.navigate(NavigationBar) }
            )
        }
        composable<NavigationBar> {
            NavigationBarScreen()
        }
    }
}