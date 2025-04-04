package com.example.idatapp.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.idatapp.Screens.HomeScreen
import com.example.idatapp.Screens.LoginScreen
import com.example.idatapp.Screens.SplashScreen

@Composable
fun AppNavigation()
{
    val navController = rememberNavController()
    NavHost(navController = navController,
            startDestination = AppScreens.SplashScreen.route)
    {
        composable(AppScreens.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(AppScreens.LoginScreen.route) {
            LoginScreen(navController)
        }
        composable(AppScreens.HomeScreen.route) {
            HomeScreen(navController)
        }
    }
}