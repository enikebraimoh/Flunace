package com.enike.flunace.ui.auth.createaccount

import android.content.res.Configuration.UI_MODE_NIGHT_YES
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
fun CreateAccountScreen(navigate: (phone: String) -> Unit) {
    val (text, setText) = remember { mutableStateOf("") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(60.dp))
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
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Done
            ),
            onTextChange = {
                if (it.length <= 10) setText(it)
            },
            leadingIcon = {
                Text(
                    text = "+234",
                    style = MaterialTheme.typography.subtitle1,
                )
            })

        Spacer(modifier = Modifier.height(20.dp))

        DefaultButton(buttonText = "Next", isEnabled = text.isNotBlank(),
            buttonClicked = { navigate(text) })

    }
}

@Preview()
@Preview(name = "default preview", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreview() {
    FlunaceTheme {
        Surface() {
            CreateAccountScreen({})
        }
    }
}

