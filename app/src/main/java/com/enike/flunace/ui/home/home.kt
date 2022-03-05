package com.enike.flunace.ui.home

import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.res.painterResource
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

            }, shape = RoundedCornerShape(50)) {
                Icon(painter = painterResource(id = R.drawable.ic_vendor), contentDescription ="" )

            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
            BottomAppBar(
                elevation = 5.dp,
                cutoutShape = RoundedCornerShape(50),
                backgroundColor = MaterialTheme.colors.background,
                content = {
                    BottomNavigation(backgroundColor = MaterialTheme.colors.background) {
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
                                selectedItem.value = "home"
                                content.value = "Home Screen"
                            },
                            icon = {
                                Icon(painter = painterResource(id = R.drawable.ic_history_icon), contentDescription ="" )
                            },
                            label = {
                                Text(text = "Home")
                            },
                            alwaysShowLabel = false
                        )
                        Spacer(modifier = Modifier.width(50.dp))
                        BottomNavigationItem(
                            selected = selectedItem.value == "cart",
                            onClick = {
                                selectedItem.value = "home"
                                content.value = "Home Screen"
                            },
                            icon = {

                                Icon(painter = painterResource(id = R.drawable.ic_cart_icon), contentDescription ="" )
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
                                Icon(imageVector = Icons.Default.Settings, contentDescription = "")
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
