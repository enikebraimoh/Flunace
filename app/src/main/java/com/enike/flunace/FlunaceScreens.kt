package com.enike.flunace

enum class FlunaceScreens() {

    SplashScreen,
    WelcomeScreen,
    CreateAccountScreen,
    VerifyOtpScreen;

    companion object {
        fun fromRoute(route: String?): FlunaceScreens =
            when (route?.substringBefore("/")) {
                SplashScreen.name -> SplashScreen
                WelcomeScreen.name -> WelcomeScreen
                CreateAccountScreen.name -> CreateAccountScreen
                VerifyOtpScreen.name -> VerifyOtpScreen

                null -> SplashScreen
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}