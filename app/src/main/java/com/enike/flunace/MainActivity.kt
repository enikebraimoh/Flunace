package com.enike.flunace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.enike.flunace.FlunaceScreens.*
import com.enike.flunace.ui.auth.addressyou.WhatsYourNameScreen
import com.enike.flunace.ui.auth.createaccount.CreateAccountScreen
import com.enike.flunace.ui.auth.verifyotp.VerifyOtpScreen
import com.enike.flunace.ui.home.Home
import com.enike.flunace.ui.picklocation.Map
import com.enike.flunace.ui.splashscreen.SplashScreen
import com.enike.flunace.ui.theme.FlunaceTheme
import com.enike.flunace.ui.welcomescreen.WelcomeScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlunaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    App()
                }
            }
        }
    }
}


@Composable
fun App() {
    val navController = rememberNavController()
    val backstackEntry = navController.currentBackStackEntryAsState()
    val currentScreen = FlunaceScreens.fromRoute(
        backstackEntry.value?.destination?.route
    )

    val systemUiController = rememberSystemUiController()
    val useDarkIcons = MaterialTheme.colors.isLight

    SideEffect {
        if (FlunaceScreens.fromRoute(navController.currentDestination?.route) == FlunaceScreens.fromRoute(
                SplashScreen.name
            )
        ) {
            systemUiController.setSystemBarsColor(
                color = Color(0xFFEF8A52),
                darkIcons = useDarkIcons
            )
        } else {
            systemUiController.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = useDarkIcons
            )
        }
    }

    Scaffold { inner_padding ->
        FlunaceNavHost(
            navController = navController,
            modifier = Modifier.padding(inner_padding)
        )
    }
}

@Composable
fun FlunaceNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = SplashScreen.name,
        modifier = modifier
    ) {

        composable(SplashScreen.name) {
            SplashScreen(navigate = {
                navController.navigate(WelcomeScreen.name)
            })
        }

        composable(WelcomeScreen.name) {
            WelcomeScreen(navigate = { navController.navigate(CreateAccountScreen.name) })
        }

        composable(CreateAccountScreen.name) {
            CreateAccountScreen(navigate = { phone ->
                navController.navigate("${VerifyOtpScreen.name}/$phone")
            })
        }

        composable(AddressYou.name) {
            WhatsYourNameScreen(navigate = {
                navController.navigate(PickLocationScreen.name)
            })
        }

        composable(PickLocationScreen.name) {
            Map()
        }

        composable(
            route = "${VerifyOtpScreen.name}/{phone}",
            arguments = listOf(
                navArgument("phone") {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            val phoneNumber = entry.arguments?.getString("phone")
            VerifyOtpScreen(navigate = {
                navController.navigate(AddressYou.name)
            }, arg = phoneNumber.toString())
        }

        composable(Home.name) {
            Home()
        }

    }


}

