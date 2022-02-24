package com.enike.flunace.ui.auth.addressyou

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.enike.flunace.ui.components.CustomInputField
import com.enike.flunace.ui.components.DefaultButton
import com.enike.flunace.ui.theme.FlunaceTheme

@Composable
fun WhatsYourNameScreen( navigate : ()  -> Unit) {
    val (firstName, setFirstNameText) = remember { mutableStateOf("") }
    val (lastName, setLastNameText) = remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        Text(
            text = "Whats your Name?",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h5
        )

        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Let us know how to properly address you",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body2
        )
        Spacer(modifier = Modifier.height(60.dp))
        CustomInputField(
            text = firstName,
            hint = "Enter your first name",
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            onTextChange = {
                if (it.length <= 10) setFirstNameText(it)
            })
        Spacer(modifier = Modifier.height(10.dp))
        CustomInputField(
            text = lastName,
            hint = "Enter your last name",
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            onTextChange = {
                if (it.length <= 10) setLastNameText(it)
            })

        Spacer(modifier = Modifier.height(60.dp))
        DefaultButton(buttonText = "Next", isEnabled = lastName.isNotBlank(),
            buttonClicked = { })
    }

}

@Preview(name = "preview")
@Composable
fun DefaultPreview() {
    FlunaceTheme {
        Surface() {
            WhatsYourNameScreen({})
        }
    }
}