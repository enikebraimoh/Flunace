package com.enike.flunace.ui.splashscreen

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.enike.flunace.Greeting
import com.enike.flunace.ui.theme.FlunaceTheme

@Composable
fun SplashScreen(){
    Surface(color = MaterialTheme.colors.primary,
        modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.padding(24.dp),
            contentAlignment = Alignment.Center,
        ){
            Text(text = "Flunace",
                style = MaterialTheme.typography.h2
            )
        }
    }
}


@Preview( name = "default preview" ,showBackground = true)
@Composable
fun DefaultPreview() {
    FlunaceTheme {
        SplashScreen()
    }
}