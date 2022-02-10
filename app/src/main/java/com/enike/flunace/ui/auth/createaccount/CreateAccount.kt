package com.enike.flunace.ui.auth.createaccount

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.enike.flunace.ui.components.CustomInputField
import com.enike.flunace.ui.components.DefaultButton
import com.enike.flunace.ui.theme.FlunaceTheme

@Composable
fun CreateAccountScreen() {
    val (text, setText) = remember { mutableStateOf("") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Create an Account",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h5
        )

        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Enter your phone number\nand we will text you a verification Code",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body2
        )
        Spacer(modifier = Modifier.height(60.dp))
        CustomInputField(
            text = text,
            onTextChange = setText,
            leadingIcon = {
                Text(
                    text = "+234",
                    style = MaterialTheme.typography.subtitle1,
                )
            })

        Spacer(modifier = Modifier.height(20.dp))

        DefaultButton(buttonText = "Next", isEnabled = text.isNotBlank(), buttonClicked = {

        })
        
    }
}

@Preview()
@Preview(name = "default preview", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreview() {
       FlunaceTheme {
           Surface() {
               CreateAccountScreen()
           }
       }
}

