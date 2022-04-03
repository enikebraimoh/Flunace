package com.enike.flunace.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.enike.flunace.ui.components.ExploreScreen
import com.enike.flunace.ui.components.Home

@Composable
fun BottomNavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = BottomBarScreen.Home.route) {
        composable(BottomBarScreen.Explore.route) {
            ExploreScreen()
        }
        composable(BottomBarScreen.History.route) {
            ExploreScreen()
        }
        composable(BottomBarScreen.Cart.route) {
            ExploreScreen()
        }
        composable(BottomBarScreen.Home.route) {
            Home()
        }

    }

}