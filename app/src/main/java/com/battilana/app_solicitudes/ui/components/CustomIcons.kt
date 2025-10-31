package com.battilana.app_solicitudes.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.battilana.app_solicitudes.R

@Composable
fun BattiIcons(
    modifier: Modifier = Modifier,
    icon: Int
){
    Icon(
        modifier = modifier
            .clickable{}
            .padding(15.dp),
        painter = painterResource(id = icon), contentDescription = null
    )
}