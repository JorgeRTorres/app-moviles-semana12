package com.example.idatapp.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.idatapp.Model.Usuario
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun DBMenuScreen(navController: NavController)
{
    Body()
}
@Composable
fun Body()
{
    // Estado para la lista de usuarios
    val usuario = remember { mutableStateOf(mutableListOf<Usuario>()) }

    //esto debe venir de la bd
//    val user1 = Usuario("U001","Juanito","Torres","12345678",1500)
//    val user2 = Usuario("U002","Jorge","Renato","74746384",6000)
//    val user3 = Usuario("U003","Renato","Torres","98765421",3500)
//    val listado = mutableListOf(user1,user2,user3)

    Column(modifier = Modifier.fillMaxSize().background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)
    {
        InputData()
        val listado = getDataFirebase()
        //ListadoData(listado)
    }

}
fun getDataFirebase():MutableList<Usuario>
{
    val listaUsuarios: MutableList<Usuario>
    listaUsuarios = mutableListOf()

    val db = Firebase.firestore
    db.collection("Usuarios")
        .get()
        .addOnSuccessListener { result ->
            for (document in result) {
                //Log.d(TAG, "${document.id} => ${document.data}")
                val usuarioID = document.get("UsuarioID").toString()
                val nombres = document.get("Nombres").toString()
                val apellidos = document.get("Apellidos").toString()
                val dni = document.get("DNI").toString()
                val sueldo = document.get("Sueldo").toString()
                val usuario = Usuario(usuarioID,nombres,apellidos,dni,sueldo.toDouble())
                listaUsuarios.add(usuario)

            }
            //ListadoData(listaUsuarios)
        }
        .addOnFailureListener { exception ->
            //Log.w(TAG, "Error getting documents.", exception)
        }
    return listaUsuarios
}
@Composable
fun ListadoData(usuario: MutableList<Usuario>)
{
    LazyColumn (modifier = Modifier.fillMaxWidth().padding(12.dp)){
        items(usuario)
        {
            item: Usuario ->
            Text (text = item.usuarioID)
            Spacer(modifier = Modifier.padding(vertical = 7.dp))
            Text(text = item.nombres)
            Spacer(modifier = Modifier.padding(vertical = 7.dp))
            Text(text = item.apellidos)
            Spacer(modifier = Modifier.padding(vertical = 7.dp))
            Text(text = item.dni)
            Spacer(modifier = Modifier.padding(vertical = 7.dp))
            Text(text = item.sueldo.toString())
            Spacer(modifier = Modifier.padding(vertical = 7.dp))
        }
    }
}

@Composable
fun InputData()
{
    Column (modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)
    {
        val nombres = remember { mutableStateOf("") }
        val apellidos = remember { mutableStateOf("") }
        val DNI = remember { mutableStateOf("") }
        val sueldo = remember { mutableStateOf("") }

        Text(modifier = Modifier.size(18.dp).fillMaxWidth(),
            text = "Registro de nuevo usuario")
        Spacer(modifier = Modifier.padding(vertical = 6.dp))
        OutlinedTextField(
            singleLine = true,
            label = { Text(text = "Ingresa tus nombres") },
            value = nombres.value,
            onValueChange = {nombres.value = it}
        )
        Spacer(modifier = Modifier.padding(vertical = 6.dp))
        OutlinedTextField(
            singleLine = true,
            label = { Text(text = "Ingresa tus apellidos") },
            value = apellidos.value,
            onValueChange = {apellidos.value = it}
        )
        Spacer(modifier = Modifier.padding(vertical = 6.dp))
        OutlinedTextField(
            singleLine = true,
            label = { Text(text = "Ingresa tu DNI") },
            value = DNI.value,
            onValueChange = {DNI.value = it}
        )
        Spacer(modifier = Modifier.padding(vertical = 6.dp))
        OutlinedTextField(
            singleLine = true,
            label = { Text(text = "Ingresa tu sueldo") },
            value = sueldo.value,
            onValueChange = {sueldo.value = it}
        )
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        Button(
            onClick = {}) {
            Text(text = "Registrar")
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun Previo(){
    Body()
}