package com.example.idatapp.Navigation

import androidx.compose.runtime.MutableState
import com.example.idatapp.R

data class BottomNavigationItem(
    val nombre: String="",
    val icono: Int=0,
    val route: String=""
)
    {
    fun listaItems(): MutableList<BottomNavigationItem> {
        var lista: MutableList<BottomNavigationItem>
        lista = mutableListOf(
            BottomNavigationItem(
                "Home",
                R.drawable.homeicon,
                MenuScreens.HomeMenuScreen.route
            ),
            BottomNavigationItem(
                "DB",
                R.drawable.dbicon,
                MenuScreens.DBMenuScreen.route
            ),
            BottomNavigationItem(
                "Apis",
                R.drawable.apisicon,
                MenuScreens.ApisMenuScreen.route
            ),
            BottomNavigationItem(
                "Settings",
                R.drawable.settingsicon,
                MenuScreens.SettingsMenuScreen.route
            )
        )
        return lista
    }
}
