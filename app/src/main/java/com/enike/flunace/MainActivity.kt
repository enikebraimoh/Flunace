package com.enike.flunace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.enike.flunace.FlunaceScreens.*
import com.enike.flunace.ui.auth.createaccount.CreateAccountScreen
import com.enike.flunace.ui.auth.createaccount.verifyotp.VerifyOtpScreen
import com.enike.flunace.ui.splashscreen.SplashScreen
import com.enike.flunace.ui.theme.FlunaceTheme
import com.enike.flunace.ui.welcomescreen.WelcomeScreen

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

        composable(
            route = "${VerifyOtpScreen.name}/{phone}",
            arguments = listOf(
                navArgument("phone") {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            val phoneNumber = entry.arguments?.getString("phone")
            VerifyOtpScreen(navigate = { }, arg = phoneNumber.toString())
        }

    }


}

