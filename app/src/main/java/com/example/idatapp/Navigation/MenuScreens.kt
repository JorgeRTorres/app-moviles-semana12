package com.example.idatapp.Navigation

sealed class MenuScreens(val route: String)
{
    object HomeMenuScreen: MenuScreens("Home_Menu_Screen")
    object DBMenuScreen: MenuScreens("DB_Menu_Screen")
    object ApisMenuScreen: MenuScreens("Apis_Menu_Screen")
    object SettingsMenuScreen: MenuScreens("Settings_Menu_Screen")
}