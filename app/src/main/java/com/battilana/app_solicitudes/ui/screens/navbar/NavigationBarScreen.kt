package com.battilana.app_solicitudes.ui.screens.navbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.battilana.app_solicitudes.R
import com.battilana.app_solicitudes.navigate.Home
import com.battilana.app_solicitudes.navigate.Pedido
import com.battilana.app_solicitudes.navigate.Profile
import com.battilana.app_solicitudes.navigate.ReportHistory
import com.battilana.app_solicitudes.ui.screens.historialpedidos.HistorialPedidosScreen
import com.battilana.app_solicitudes.ui.screens.home.HomeScreen
import com.battilana.app_solicitudes.ui.screens.pedido.PedidoScreen
import com.battilana.app_solicitudes.ui.screens.profile.ProfileScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationBarScreen(
    onLogout: () -> Unit
) {

    val itemsNav = listOf(
        NavItem(name = "Inicio", icon = Icons.Default.Home, route = Home),
        NavItem(
            name = "Nuevo",
            icon = ImageVector.vectorResource(R.drawable.ic_pckg_add),
            route = Pedido
        ),
        NavItem(
            name = "Solicitudes",
            icon = ImageVector.vectorResource(R.drawable.ic_form),
            route = ReportHistory
        ),
        NavItem(name = "Perfil", icon = Icons.Default.Person, route = Profile)
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
            composable<Profile> {
                ProfileScreen(onLogout = onLogout)
            }
            composable<ReportHistory> { HistorialPedidosScreen() }
        }
    }
}

@Composable
fun RowScope.NavItems(navItem: NavItem, isSelected: Boolean, onIndexChange: () -> Unit) {
    NavigationBarItem(
        modifier = Modifier.size(30.dp),
        selected = isSelected,
        onClick = onIndexChange,
        label = {
            Text(navItem.name)
        },
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