package com.enike.flunace.ui.auth.createaccount.verifyotp

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.enike.flunace.ui.components.DefaultButton
import com.enike.flunace.ui.components.OtpBugView
import com.enike.flunace.ui.theme.FlunaceTheme

@ExperimentalComposeUiApi
@Composable
fun VerifyOtpScreen(
    navigate: () -> Unit,
    arg: String
) {
    val (editValue, setEditValue) = remember { mutableStateOf("") }
    val otpLength = remember { 4 }
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        Text(
            text = "Verify Phone Number",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h5
        )

        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Enter the code from the SMS \n   sent to you at\n" +
                    arg.toString(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body2
        )
        Spacer(modifier = Modifier.height(60.dp))

        OtpBugView(
            otpLength = otpLength,
            editTextValue = editValue,
            setEditTextValue = setEditValue
        )


        Spacer(modifier = Modifier.height(10.dp))

        DefaultButton(buttonText = "Verify",
            isEnabled = editValue.length == otpLength,
            buttonClicked = {
                Toast.makeText(context, editValue, Toast.LENGTH_SHORT).show()
            })

        Spacer(modifier = Modifier.height(60.dp))
    }
}

@ExperimentalComposeUiApi
@Preview(
    name = "default preview",
    showBackground = true,
)
@Composable
fun DefaultPreview() {
    FlunaceTheme {
        Surface() {
            VerifyOtpScreen({},"")
        }
    }
}