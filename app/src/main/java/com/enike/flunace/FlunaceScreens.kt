package com.enike.flunace

enum class FlunaceScreens() {

    SplashScreen,
    WelcomeScreen,
    CreateAccountScreen,
    VerifyOtpScreen,
    AddressYou,
    Home,
    PickLocationScreen;

    companion object {
        fun fromRoute(route: String?): FlunaceScreens =
            when (route?.substringBefore("/")) {
                SplashScreen.name -> SplashScreen
                WelcomeScreen.name -> WelcomeScreen
                CreateAccountScreen.name -> CreateAccountScreen
                VerifyOtpScreen.name -> VerifyOtpScreen
                AddressYou.name -> AddressYou
                PickLocationScreen.name -> PickLocationScreen
                Home.name -> Home
                null -> SplashScreen
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}