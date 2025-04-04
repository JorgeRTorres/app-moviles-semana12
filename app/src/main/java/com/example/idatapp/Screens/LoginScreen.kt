package com.example.idatapp.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.idatapp.Navigation.AppScreens
import com.example.idatapp.R

@Composable
fun LoginScreen(navController: NavController)
{
    val usuarioTF = remember { mutableStateOf("") }
    val passwordTF = remember { mutableStateOf("") }
    Column(Modifier.fillMaxSize(),
           verticalArrangement = Arrangement.Center,
           horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(R.drawable.logo), contentDescription = "")
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        OutlinedTextField(
            value = usuarioTF.value,
            label = {Text(text = "Escribe tu usuario")},
            singleLine = true,
            onValueChange = {usuarioTF.value = it})
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        OutlinedTextField(
            value = passwordTF.value,
            label = { Text(text = "Escribe tu password") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = {passwordTF.value = it})
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        Button(onClick = {
            navController.popBackStack()
            navController.navigate(AppScreens.HomeScreen.route)
        }) {
            Text(text = "Iniciar sesión")
        }
        Spacer(modifier = Modifier.padding(vertical = 15.dp))
        Text(text = "Olvidé mi contrsaña")
    }
}