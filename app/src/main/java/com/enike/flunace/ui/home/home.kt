package com.enike.flunace.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.enike.flunace.R
import com.enike.flunace.ui.theme.FlunaceTheme

@Composable
fun Home() {

    val selectedItem = remember { mutableStateOf("home") }
    val content = remember { mutableStateOf("Home Screen") }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {

                selectedItem.value = "stores"
                content.value = "Stores Screen"

            }, shape = RoundedCornerShape(50)) {
                Icon(painter = painterResource(id = R.drawable.ic_vendor), contentDescription = "")

            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
            BottomAppBar(
                elevation = 20.dp,
                cutoutShape = RoundedCornerShape(50),
                backgroundColor = MaterialTheme.colors.background,
                content = {
                    BottomNavigation(
                        elevation = 20.dp,
                        backgroundColor = MaterialTheme.colors.background
                    ) {
                        BottomNavigationItem(
                            selected = selectedItem.value == "home",
                            onClick = {
                                selectedItem.value = "home"
                                content.value = "Home Screen"
                            },
                            icon = {
                                Icon(imageVector = Icons.Default.Home, contentDescription = "")
                            },
                            label = {
                                Text(text = "Home")
                            },
                            alwaysShowLabel = false
                        )
                        BottomNavigationItem(
                            selected = selectedItem.value == "history",
                            onClick = {
                                selectedItem.value = "history"
                                content.value = "History Screen"
                            },
                            icon = {
                                Icon(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_history_icon),
                                    contentDescription = ""
                                )
                            },
                            label = {
                                Text(text = "history")
                            },
                            alwaysShowLabel = false
                        )
                        Spacer(modifier = Modifier.width(50.dp))
                        BottomNavigationItem(
                            selected = selectedItem.value == "cart",
                            onClick = {
                                selectedItem.value = "cart"
                                content.value = "Cart Screen"
                            },
                            icon = {
                                Icon(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_cart_icon),
                                    contentDescription = ""
                                )
                            },
                            label = {
                                Text(text = "Cart")
                            },
                            alwaysShowLabel = false
                        )
                        BottomNavigationItem(
                            selected = selectedItem.value == "settings",
                            onClick = {
                                selectedItem.value = "settings"
                                content.value = "Settings Screen"
                            },
                            icon = {
                                Icon(imageVector = Icons.Default.Settings, contentDescription = "")
                            },
                            label = {
                                Text(text = "Settings")
                            },
                            alwaysShowLabel = false
                        )

                    }
                }
            )
        }
    ) {
        Box(
            Modifier
                .background(Color.Blue)
                .padding(it)) {

        }
    }
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
