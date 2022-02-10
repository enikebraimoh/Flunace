package com.enike.flunace


import com.enike.flunace.ui.splashscreen.SplashScreen
import com.enike.flunace.ui.welcomescreen.WelcomeScreen

enum class FlunaceScreens() {

    SplashScreen(),
    WelcomeScreen();

    companion object {
        fun fromRoute(route: String?): FlunaceScreens =
            when (route?.substringBefore("/")) {
                SplashScreen.name -> SplashScreen
                WelcomeScreen.name -> WelcomeScreen
                null -> SplashScreen
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}