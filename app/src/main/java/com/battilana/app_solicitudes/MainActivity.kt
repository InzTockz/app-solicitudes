package com.battilana.app_solicitudes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.battilana.app_solicitudes.ui.theme.AppsolicitudesTheme
import com.battilana.app_solicitudes.view.navigate.NavigationWrapper

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppsolicitudesTheme {
                NavigationWrapper()
            }
        }
    }
}