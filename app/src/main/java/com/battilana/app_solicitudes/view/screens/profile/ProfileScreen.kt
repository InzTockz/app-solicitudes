package com.battilana.app_solicitudes.view.screens.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.battilana.app_solicitudes.R

@Composable
fun ProfileScreen() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Spacer(Modifier.height(40.dp))
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.1f),
            elevation = CardDefaults.elevatedCardElevation(5.dp)
        ) {
            Spacer(Modifier.height(20.dp))
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_logo_battilana),
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp)
                        .border(BorderStroke(2.dp, Color.White), shape = CircleShape),
                    contentScale = ContentScale.FillBounds
                )
                Spacer(Modifier.height(20.dp))
                Text(text = "Usuario Battilana")
                Row {
                    Text(text = "Marketing")
                    Spacer(Modifier.width(10.dp))
                    Text(text = "USER10001")
                }
            }

        }
        Spacer(Modifier.height(20.dp))
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.62f),
            elevation = CardDefaults.elevatedCardElevation(5.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                Text(text = "Información personal")
                Text(text = "Actualiza tus datos")
                Spacer(Modifier.height(20.dp))
                Text(text = "Nombre completo")
                Text(text = "Prueba Testing")
                Spacer(Modifier.height(20.dp))
                Text(text = "Correo Eletronico")
                Text(text = "pruebatesting@battilana.biz")
                Spacer(Modifier.height(20.dp))
                Text(text = "Telefono")
                Text(text = "+51 958465120")
                Spacer(Modifier.height(20.dp))
                Row {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        Text(text = "Departamento")
                        Text(text = "Marketing")
                    }
                    Column (
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)

                    ) {
                        Text(text = "Id empleado")
                        Text(text = "USER10001")
                    }
                }
                Spacer(Modifier.height(35.dp))
                Button (
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(25),
                    contentPadding = PaddingValues(10.dp),
                    onClick = {}) {
                    Text(modifier = Modifier
                        .padding(10.dp), text = "Guardar cambios")
                }
                Spacer(Modifier.height(10.dp))
                Button (
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(25),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red, contentColor = Color.White),
                    contentPadding = PaddingValues(10.dp),
                    onClick = {}) {
                    Text(modifier = Modifier
                        .padding(10.dp), text = "Cerrar sesion")
                }
            }

        }
        Spacer(Modifier.height(15.dp))
    }


}