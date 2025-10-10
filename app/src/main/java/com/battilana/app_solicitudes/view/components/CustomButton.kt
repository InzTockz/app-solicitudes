package com.battilana.app_solicitudes.view.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape

@Composable
fun BattiButton(
    modifier: Modifier = Modifier,
    onClick:() -> Unit,
    shape: Shape = RoundedCornerShape(25),
    text: String
){
    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        shape = shape
    ) {
        Text(text = text)
    }
}

@Composable
fun BattiOutLinedButton(
    modifier: Modifier = Modifier,
    onClick:() -> Unit,
    shape: Shape = RoundedCornerShape(25),
    text: String
){
    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        shape = shape
    ) {
        Text(text = text)
    }
}