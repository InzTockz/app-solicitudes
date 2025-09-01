package com.battilana.app_solicitudes.view.screens.navbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
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
import com.battilana.app_solicitudes.R

@Composable
fun NavigationBarScreen(
) {
    val itemsNav = listOf(
        NavItem(name = "Inicio", icon = Icons.Default.Home),
        NavItem(name = "Nuevo", icon = Icons.Default.Add),
        NavItem(name = "Solicitudes", icon = ImageVector.vectorResource(R.drawable.ic_form)),
        NavItem(name = "Perfil", icon = Icons.Default.Person)
    )

    var isSelected by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                itemsNav.forEachIndexed { index, item ->
                    NavItems(navItem = item, isSelected = isSelected == index) {
                        isSelected = index
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {

        }
    }
}

@Composable
fun RowScope.NavItems(navItem: NavItem, isSelected: Boolean, onIndexChange: () -> Unit) {
    NavigationBarItem(
        selected = isSelected,
        onClick = { onIndexChange() },
        label = { Text(navItem.name) },
        alwaysShowLabel = false,
        icon = {
            Icon(imageVector = navItem.icon, contentDescription = null)
        }
    )
}

data class NavItem(
    val name: String,
    val icon: ImageVector
)