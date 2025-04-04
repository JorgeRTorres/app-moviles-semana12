package com.example.idatapp.Screens

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.example.idatapp.Navigation.BottomNavigationItem
import com.example.idatapp.Navigation.MenuNavigation
import com.example.idatapp.Navigation.MenuScreens
import com.example.idatapp.R

@Composable
fun HomeScreen(navController: NavController)
{
    //BottomNavigationBar()
    MenuNavigation()
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNavigationBar(navController: NavController)
{
    var itemSeleccionado = remember { mutableIntStateOf(0) }
    //val navController = rememberNavController()
    Scaffold(modifier = Modifier.fillMaxSize(),
            bottomBar =
            {
                NavigationBar{BottomNavigationItem().listaItems().forEachIndexed{ index, item ->
                    NavigationBarItem(
                        selected = itemSeleccionado.intValue == index,
                        label = { Text(text = item.nombre) },
                        icon = { Icon(painterResource(item.icono), contentDescription = "") },
                        onClick = {
                            itemSeleccionado.intValue = index
                            navController.navigate(item.route)
                            {
                                //popUpTo(navController.graph.findStartDestination())
                                popUpTo(item.route)
                                {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                    )
                    }
                }
            })
    {
    }
}