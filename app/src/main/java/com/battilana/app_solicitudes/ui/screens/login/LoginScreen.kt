package com.battilana.app_solicitudes.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.battilana.app_solicitudes.R
import com.battilana.app_solicitudes.ui.components.BattiTextField
import com.battilana.app_solicitudes.ui.components.BattiTextFieldPassword

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    navigation: () -> Unit
) {

    val uiState by loginViewModel.uiStateLogin.collectAsStateWithLifecycle()

    Scaffold(
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.weight(1f))
            ElevatedCard(
                elevation = CardDefaults.cardElevation(defaultElevation = 15.dp),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.login_screen_text_title),
                        style = MaterialTheme.typography.titleLarge
                    )
                    Image(
                        painter = painterResource(R.drawable.ic_logo_battilana), contentDescription = null)
                    Text(
                        text = stringResource(R.string.login_screen_text_secondary),
                        style = MaterialTheme.typography.labelMedium
                    )
                    Spacer(Modifier.height(10.dp))
                    BattiTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = uiState.username,
                        onValueChange = { loginViewModel.onUsernameChange(it)},
                        label = stringResource(R.string.login_screen_textfield_text_usuario)
                    )
                    Spacer(Modifier.height(2.dp))
                    BattiTextFieldPassword(
                        modifier = Modifier.fillMaxWidth(),
                        value = uiState.password,
                        onTextFieldValue = { loginViewModel.onPasswordChange(it)},
                        label = stringResource(R.string.login_screen_textfield_text_contraseña),
                        visualTransformation = uiState.viewPassword,
                        iconButtonAction = { loginViewModel.onShowPasswordChange()},
                        iconImage = uiState.viewPassword
                    )
                    Spacer(Modifier.height(20.dp))
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            loginViewModel.login { navigation() }
                        },
                        shape = RoundedCornerShape(30),
                        enabled = uiState.enabledButtonLogin && !uiState.isLoading
                    ) {
                        if (uiState.isLoading){
                            CircularProgressIndicator(
//                                color = Color.White,
                                strokeWidth = 2.dp,
                                modifier = Modifier.size(20.dp)
                            )
                        } else {
                            Text(
                                modifier = Modifier.padding(vertical = 5.dp),
                                text = stringResource(R.string.login_screen_button_text_login)
                            )
                        }
                    }
                }
            }
            Spacer(Modifier.weight(1f))
        }
    }
}