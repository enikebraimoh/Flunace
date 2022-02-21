package com.enike.flunace.ui.auth.createaccount.verifyotp

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.enike.flunace.ui.components.OtpBugView
import com.enike.flunace.ui.theme.FlunaceTheme

@ExperimentalComposeUiApi
@Composable
fun VerifyOtpScreen() {
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
            text = "Enter the code from the SMS sent to you at\n" +
                    "+2348140252210",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body2
        )
        Spacer(modifier = Modifier.height(60.dp))

        OtpBugView()

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
            VerifyOtpScreen()
        }
    }
}