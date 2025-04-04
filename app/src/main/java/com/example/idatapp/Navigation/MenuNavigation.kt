package com.example.idatapp.Navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.example.idatapp.Screens.ApisMenuScreen
import com.example.idatapp.Screens.BottomNavigationBar
import com.example.idatapp.Screens.DBMenuScreen
import com.example.idatapp.Screens.HomeMenuScreen
import com.example.idatapp.Screens.SettingsMenuScreen

@Composable
fun MenuNavigation()
{
    val navController = rememberNavController()
    BottomNavigationBar(navController)
    NavHost(navController = navController,
        startDestination = MenuScreens.HomeMenuScreen.route)
    {
        composable(MenuScreens.HomeMenuScreen.route) {
            HomeMenuScreen(navController)
        }
        composable(MenuScreens.DBMenuScreen.route) {
            DBMenuScreen(navController)
        }
        composable(MenuScreens.ApisMenuScreen.route) {
            ApisMenuScreen(navController)
        }
        composable(MenuScreens.SettingsMenuScreen.route) {
            SettingsMenuScreen(navController)
        }
    }
}



/*Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->

        val graph =
            navController.createGraph(startDestination = MenuScreens.HomeMenuScreen.route) {
                composable(route = MenuScreens.HomeMenuScreen.route) {
                    HomeMenuScreen(navController)
                }
                composable(route = MenuScreens.DBMenuScreen.route) {
                    DBMenuScreen(navController)
                }
                composable(route = MenuScreens.ApisMenuScreen.route) {
                    ApisMenuScreen(navController)
                }
                composable(route = MenuScreens.SettingsMenuScreen.route) {
                    SettingsMenuScreen(navController)
                }
            }
        NavHost(
            navController = navController,
            graph = graph,
            modifier = Modifier.padding(innerPadding)
        )

    }*/