package com.enike.flunace.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.enike.flunace.ui.theme.FlunaceTheme
import com.enike.flunace.ui.theme.myColor

@Composable
fun DefaultButton(
    buttonText: String,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 40.dp),
    buttonClicked: () -> Unit,
    isEnabled: Boolean = true,
) {
    Button(
        modifier = modifier,
        enabled = isEnabled,
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 8.dp,
            disabledElevation = 0.dp
        ),
        colors = ButtonDefaults.buttonColors(
            disabledBackgroundColor = Color(0xFFFBE5D9)
        ),
        shape = MaterialTheme.shapes.small.copy(all = CornerSize(20.dp)),
        onClick = { buttonClicked() }
    ) {
        Text(
            text = buttonText,
            Modifier.padding(15.dp),
            style = MaterialTheme.typography.button
        )
    }

}


@Composable
fun CustomInputField(
    modifier: Modifier = Modifier
        .padding(5.dp)
        .fillMaxWidth(),
    text: String,
    singleLine : Boolean = true,
    onTextChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions =
        KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
    shapes: Shape = MaterialTheme.shapes.small.copy(all = CornerSize(15.dp)),
    leadingIcon: @Composable (() -> Unit)? = null,
    hint: String = "8140252210"
) {
    Surface(color = MaterialTheme.colors.myColor, shape = shapes) {
        TextField(
            modifier = modifier.padding(horizontal = 20.dp, vertical = 5.dp),
            value = text,
            onValueChange = onTextChange,
            textStyle = MaterialTheme.typography.subtitle1,
            placeholder = {
                Text(
                    text = hint,
                    style = MaterialTheme.typography.subtitle1,
                )
            },
            singleLine = singleLine,
            keyboardOptions = keyboardOptions,
            colors = TextFieldDefaults.textFieldColors(
                disabledTextColor = Color.Transparent,
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            leadingIcon = leadingIcon
        )
    }

}


@Composable
fun OtpCell(
    modifier: Modifier = Modifier,
    value: String,
) {
    // UI for OTP Character
    Box(
        modifier = if (value.isNotEmpty()) modifier.background(
            Color(0xFFFBE5D9),
            shape = MaterialTheme.shapes.small.copy(all = CornerSize(15.dp))
        )
        else modifier.background(
            MaterialTheme.colors.myColor,
            shape = MaterialTheme.shapes.small.copy(all = CornerSize(15.dp))
        )

    ) {
        Text(
            text = value,
            color = Color.Black,
            style = MaterialTheme.typography.h6,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(10.dp)
        )
    }
}


@Composable
fun OtpBugView(
    modifier: Modifier = Modifier,
    editTextValue: String,
    otpLength: Int,
    setEditTextValue: (String) ->  Unit,
) {
    val focusRequester = remember { FocusRequester() }
    val keyboard = LocalSoftwareKeyboardController.current

    // hidden TextField for controlling OTP Cells UI
    TextField(
        value = editTextValue,
        onValueChange = { value ->
            if (value.length <= otpLength) {
                setEditTextValue(value)
            }
        },
        modifier = Modifier
            .size(0.dp)
            .focusRequester(focusRequester),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number
        )
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        (0 until otpLength).map { index ->
            OtpCell(
                modifier = Modifier
                    .size(80.dp)
                    .padding(10.dp)
                    .clickable {
                        focusRequester.requestFocus()
                        // this is required so if keyboard is dissmissed
                        // then could be open again with focus
                        keyboard?.show()
                    },
                value = editTextValue.getOrNull(index)?.toString() ?: ""
            )
        }
    }

}


//PREVIEWS

@Preview()
@Preview(name = "default preview", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreview() {
    FlunaceTheme {
        CustomInputField(
            text = "40890990909",
            onTextChange = {},
            leadingIcon = {
                Text(
                    text = "+234",
                    style = MaterialTheme.typography.subtitle1,
                )
            })
    }
}

@Preview(name = "Button preview")
@Composable
fun DefaultButtonPreview() {
    FlunaceTheme {
        DefaultButton(buttonText = "Button", buttonClicked = {})
    }
}


@Preview(name = "otp preview", showBackground = true)
@Composable
fun DefaultOtpPreview() {
    FlunaceTheme {
        OtpBugView( otpLength = 4,
            editTextValue = "",
            setEditTextValue = {})
    }
}

