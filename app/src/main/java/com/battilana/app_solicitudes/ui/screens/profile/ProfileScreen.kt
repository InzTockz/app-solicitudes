package com.battilana.app_solicitudes.ui.screens.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.battilana.app_solicitudes.R
import com.battilana.app_solicitudes.navigate.Login
import com.battilana.app_solicitudes.navigate.SessionViewModel
import com.battilana.app_solicitudes.ui.components.BattiButtonSimple
import com.battilana.app_solicitudes.ui.theme.battiOrangeColor

@Composable
fun ProfileScreen(
    onLogout: () -> Unit,
    profileViewModel: ProfileViewModel = hiltViewModel(),
    sessionViewModel: SessionViewModel = hiltViewModel()
) {

    val usuario by profileViewModel.usuario.collectAsState()
    val loading by profileViewModel.loading.collectAsState()

    LaunchedEffect(Unit) {
        profileViewModel.cargarUsuario()
    }

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Spacer(Modifier.height(40.dp))
        when{
            usuario != null -> {
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
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
                        Text(text = "${usuario?.names} ${usuario?.subnames}")
                        Row {
                            Text(text = "Monogastricos / Poligastricos / Administrador")
                        }
                    }
                    Spacer(Modifier.height(20.dp))
                }
                Spacer(Modifier.height(20.dp))
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    elevation = CardDefaults.elevatedCardElevation(5.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp)
                    ) {
                        Text(text = "Información personal")
                        Spacer(Modifier.height(20.dp))
                        Text(text = "Nombre completo")
                        Text(text = "${usuario?.names} ${usuario?.subnames}")
                        Spacer(Modifier.height(20.dp))
                        Text(text = "Correo Eletronico")
                        Text(text = "${usuario?.email}")
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
                                Text(text = "Sistemas")
                            }
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)

                            ) {
                                Text(text = "Tipo de Usuario")
                                Text(text =
                                    if(usuario?.roles.toString() == "USUARIO") "Usuario" else
                                    if(usuario?.roles.toString() == "ADMINISTRADOR") "Administrador" else ""
                                )
                            }
                        }
                        Spacer(Modifier.height(35.dp))
                        BattiButtonSimple(
                            modifier = Modifier
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(25),
                            contentPadding = PaddingValues(10.dp),
                            onClick = {},
                            enabled = false,
                            text = "Guardar cambios"
                        )
                        Spacer(Modifier.height(2.dp))
                        BattiButtonSimple(
                            modifier = Modifier
                                .fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = battiOrangeColor,
                                contentColor = Color.White
                            ),
                            contentPadding = PaddingValues(10.dp),
                            onClick = {
                                sessionViewModel.logout {
                                    onLogout()
                                }
                            },
                            text = "Cerrar sesion"
                        )
                    }
                }
            }
        }
        Spacer(Modifier.height(20.dp))
    }


}