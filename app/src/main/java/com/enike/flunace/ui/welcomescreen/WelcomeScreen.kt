package com.enike.flunace.ui.welcomescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.enike.flunace.R
import com.enike.flunace.ui.components.DefaultButton
import com.enike.flunace.ui.theme.FlunaceTheme

@Composable
fun WelcomeScreen() {
    Surface {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.food_user),
                contentDescription = "just a delivery guy"
            )
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colors.primary.copy(alpha = 0.3f))
                    .fillMaxSize()
            ) {
                Box(modifier = Modifier.align(Alignment.BottomCenter)) {
                    Contents()
                }
            }
        }
    }
}

@Composable
fun Contents() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(20.dp)
    ) {
        Text(
            color = Color.White,
            text = stringResource(id = R.string.welcome_title),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h3
        )
        Text(
            color = Color.White.copy(alpha = 0.7f),
            text = "Get your groceries in as fast as one hour",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.subtitle1
        )

        Spacer(modifier = Modifier.height(10.dp))

        DefaultButton(buttonClicked = {}, buttonText = "Get Started")

    }
}


@Preview(name = "default preview", showBackground = true)
@Composable
fun DefaultPreview() {
    FlunaceTheme {
        WelcomeScreen()
    }
}