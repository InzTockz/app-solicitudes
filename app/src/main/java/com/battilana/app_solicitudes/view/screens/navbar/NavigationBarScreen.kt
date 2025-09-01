package com.battilana.app_solicitudes.view.screens.navbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.battilana.app_solicitudes.R
import com.battilana.app_solicitudes.view.navigate.Home
import com.battilana.app_solicitudes.view.navigate.NavigationWrapper
import com.battilana.app_solicitudes.view.navigate.Pedido
import com.battilana.app_solicitudes.view.screens.home.HomeScreen
import com.battilana.app_solicitudes.view.screens.pedido.PedidoScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationBarScreen(
) {
    val itemsNav = listOf(
        NavItem(name = "Inicio", icon = Icons.Default.Home, route = Home),
        NavItem(name = "Nuevo", icon = Icons.Default.Add, route = Pedido),
        NavItem(name = "Solicitudes", icon = ImageVector.vectorResource(R.drawable.ic_form), route = {}),
        NavItem(name = "Perfil", icon = Icons.Default.Person, route = {})
    )

    val navController = rememberNavController()
    var isSelected by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                itemsNav.forEachIndexed { index, item ->
                    NavItems(navItem = item, isSelected = isSelected == index) {
                        isSelected = index
                        item.route.let { navController.navigate(it) }
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Home,
            modifier = Modifier
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding),
        )
        {
            composable<Home> { HomeScreen() }
            composable<Pedido> { PedidoScreen() }
        }
    }
}

@Composable
fun RowScope.NavItems(navItem: NavItem, isSelected: Boolean, onIndexChange: () -> Unit) {
    NavigationBarItem(
        selected = isSelected,
        onClick = {
            onIndexChange()
        },
        label = { Text(navItem.name) },
        alwaysShowLabel = false,
        icon = {
            Icon(imageVector = navItem.icon, contentDescription = null)
        }
    )
}

data class NavItem(
    val name: String,
    val icon: ImageVector,
    val route: Any
)