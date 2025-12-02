package com.battilana.app_solicitudes.ui.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.battilana.app_solicitudes.ui.theme.battiOrangeColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BattiTopAppBarHome(
    text: String,
    contentColor: Color = Color.White
){
    TopAppBar(
        title = {
            Text(text = text)
        },
        actions = {
            Icon(
                imageVector = Icons.Default.Notifications, contentDescription = null
            )
            Spacer(Modifier.width(20.dp))
            Icon(
                modifier = Modifier.padding(end = 20.dp),
                imageVector = Icons.Default.Person, contentDescription = null
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            titleContentColor = contentColor,
            navigationIconContentColor = contentColor,
            actionIconContentColor = contentColor
        )
    )
}