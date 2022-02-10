package com.enike.flunace.ui.splashscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.enike.flunace.ui.theme.FlunaceTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navigate: () -> Unit) {

    LaunchedEffect(key1 = 1, block = {
        delay(3000L)
        navigate()
    })

    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.padding(24.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "Flunace",
                style = MaterialTheme.typography.h2
            )
        }

    }

}


@Preview(name = "default preview", showBackground = true)
@Composable
fun DefaultPreview() {
    FlunaceTheme {
        SplashScreen({})
    }
}