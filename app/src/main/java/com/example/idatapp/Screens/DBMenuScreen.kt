package com.example.idatapp.Screens

import android.widget.Toast
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.idatapp.Model.Usuario
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

val db = Firebase.firestore

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



    Column(modifier = Modifier.fillMaxSize().background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)
    {
        InputData()
        getDataFirebase()

    }

}
//conexion a base de datos
@Composable
fun getDataFirebase()
{
    var dataUsuarios = remember { mutableStateOf<MutableList<Usuario>?>(null) }
    var context = LocalContext.current
    var cargando by remember { mutableStateOf(true) }
    val listaUsuarios: MutableList<Usuario>
    listaUsuarios = mutableListOf()
    LaunchedEffect(true)
    {
        cargando = true
        db.collection("Usuarios")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    //Log.d(TAG, "${document.id} => ${document.data}")
                    val nombres = document.get("Nombres").toString()
                    val apellidos = document.get("Apellidos").toString()
                    val dni = document.get("DNI").toString()
                    val sueldo = document.get("Sueldo").toString()
                    val usuario = Usuario(nombres, apellidos, dni, sueldo.toDouble())
                    listaUsuarios.add(usuario)

                }
                dataUsuarios.value = listaUsuarios
                cargando = false
            }
            .addOnFailureListener { exception ->
                Toast.makeText(context, "Nose pudo conectar, intente luego", Toast.LENGTH_SHORT).show()
            }
    }
    dataUsuarios.value?.let { listado ->
        ListadoData(listado)
    }
    if(cargando) {
        CircularProgressIndicator()
        Text(text = "Cargando data...")
    }
}

//findAll
@Composable
fun ListadoData(usuario: MutableList<Usuario>)
{
    LazyColumn (modifier = Modifier.fillMaxWidth().padding(12.dp)){
        items(usuario)
        {
            item: Usuario ->
            Spacer(modifier = Modifier.padding(vertical = 7.dp))
            Text(text = "Nombres: "+item.nombres)
            Spacer(modifier = Modifier.padding(vertical = 7.dp))
            Text(text = "Apellidos: "+item.apellidos)
            Spacer(modifier = Modifier.padding(vertical = 7.dp))
            Text(text = "DNI: "+item.dni)
            Spacer(modifier = Modifier.padding(vertical = 7.dp))
            Text(text = "Sueldo: "+item.sueldo.toString())
            Spacer(modifier = Modifier.padding(vertical = 7.dp))
        }
    }
}
//insertado de datos
@Composable
fun InputData()
{
    Column (modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)
    {
        var abrirComposable by remember { mutableStateOf(false) }
        var nombres by remember { mutableStateOf("") }
        var apellidos by remember { mutableStateOf("") }
        var DNI by remember { mutableStateOf("") }
        var sueldo by remember { mutableStateOf("") }

        Text(modifier = Modifier.size(18.dp).fillMaxWidth(),
            text = "Registro de nuevo usuario")
        Spacer(modifier = Modifier.padding(vertical = 6.dp))
        OutlinedTextField(
            singleLine = true,
            label = { Text(text = "Ingresa tus nombres") },
            value = nombres,
            onValueChange = {nombres = it}
        )
        Spacer(modifier = Modifier.padding(vertical = 6.dp))
        OutlinedTextField(
            singleLine = true,
            label = { Text(text = "Ingresa tus apellidos") },
            value = apellidos,
            onValueChange = {apellidos = it}
        )
        Spacer(modifier = Modifier.padding(vertical = 6.dp))
        OutlinedTextField(
            singleLine = true,
            label = { Text(text = "Ingresa tu DNI") },
            value = DNI,
            onValueChange = {DNI = it}
        )
        Spacer(modifier = Modifier.padding(vertical = 6.dp))
        OutlinedTextField(
            singleLine = true,
            label = { Text(text = "Ingresa tu sueldo") },
            value = sueldo,
            onValueChange = {sueldo = it}
        )
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        Button(
            onClick = {
                abrirComposable = true
            }) {
            Text(text = "Registrar")
        }
        if (abrirComposable){
            abrirComposable = false
            GuardarEnDB(nombres,apellidos, DNI, sueldo)
            nombres = ""
            apellidos = ""
            DNI = ""
            sueldo = ""
        }
    }
}

@Composable
fun GuardarEnDB(nombres: String, apellidos: String, dni: String, sueldo: String) {
    //var cargando2 by remember { mutableStateOf(true) }
    var guardado = remember { mutableStateOf(false) }
    var context = LocalContext.current
    val usuario = hashMapOf(
        "Nombres" to nombres,
        "Apellidos" to apellidos,
        "DNI" to dni,
        "Sueldo" to sueldo.toDouble()
    )
    //cargando2 = true
    db.collection("Usuarios")
        .add(usuario)
        .addOnSuccessListener { documentReference ->
            Toast.makeText(context,"Usuario registrado", Toast.LENGTH_SHORT).show()
            LimpiarCampos()
            guardado.value = true
            //cargando2 = false
        }
        .addOnFailureListener { e ->

        }
    if (guardado.value)
        getDataFirebase()

    /*if(cargando2) {
        CircularProgressIndicator()
        Text(text = "Guardando usuario...")
    }*/
}

fun LimpiarCampos(){

}

@Composable
@Preview(showSystemUi = true)
fun Previo(){
    Body()
}


//esto debe venir de la bd
//    val user1 = Usuario("U001","Juanito","Torres","12345678",1500)
//    val user2 = Usuario("U002","Jorge","Renato","74746384",6000)
//    val user3 = Usuario("U003","Renato","Torres","98765421",3500)
//    val listado = mutableListOf(user1,user2,user3)