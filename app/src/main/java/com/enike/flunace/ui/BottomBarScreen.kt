package com.enike.flunace.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(val route: String, val title: String, val icon: ImageVector) {

    object Cart : BottomBarScreen("cart", "Cart", Icons.Default.ShoppingCart)
    object Home : BottomBarScreen("home", "Home", Icons.Default.Home)
    object History : BottomBarScreen("history", "History", Icons.Default.Info)
    object Explore : BottomBarScreen("explore", "Explore", Icons.Default.Settings)

}