package com.enike.flunace.ui.home

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.enike.flunace.ui.auth.addressyou.WhatsYourNameScreen
import com.enike.flunace.ui.theme.FlunaceTheme

@Composable
fun Home() {

    val selectedItem = remember { mutableStateOf("home") }
    val content = remember { mutableStateOf("Home Screen") }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {

            }, shape = RoundedCornerShape(50)) {

            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
            BottomAppBar(
                content = {
                    BottomNavigation(backgroundColor = MaterialTheme.colors.background) {
                        BottomNavigationItem(
                            selected = selectedItem.value == "home",
                            onClick = {
                                selectedItem.value = "home"
                                content.value = "Home Screen"
                            },
                            icon = {
                                Icon(imageVector = Icons.Default.Home, contentDescription = "" )
                            },
                            label = {
                                Text(text = "Home")
                            },
                            alwaysShowLabel = false
                        )
                        BottomNavigationItem(
                            selected = selectedItem.value == "home",
                            onClick = {
                                selectedItem.value = "home"
                                content.value = "Home Screen"
                            },
                            icon = {
                                Icon(imageVector = Icons.Default.Settings, contentDescription = "" )
                            },
                            label = {
                                Text(text = "Home")
                            },
                            alwaysShowLabel = false
                        )
                    }
                }
            )
        }
    ) {}
}

@Preview(name = "home preview")
@Composable
fun HomePreview() {
    FlunaceTheme {
        Surface {
            Home()
        }
    }
}
