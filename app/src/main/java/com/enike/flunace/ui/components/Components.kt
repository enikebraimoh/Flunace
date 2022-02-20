package com.enike.flunace.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusOrder
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.enike.flunace.ui.theme.FlunaceTheme
import com.enike.flunace.ui.theme.myColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
        onClick = { buttonClicked }
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
    onTextChange: (String) -> Unit,
    shapes: Shape = MaterialTheme.shapes.small.copy(all = CornerSize(15.dp)),
    leadingIcon: @Composable () -> Unit,
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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
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
fun OTPTextFields(
    modifier: Modifier = Modifier,
    length: Int,
    onFilled: (code: String) -> Unit
) {
    var code: List<Char> by remember { mutableStateOf(listOf()) }
    val focusRequesters: List<FocusRequester> = remember {
        val temp = mutableListOf<FocusRequester>()

        repeat(length) {
            temp.add(FocusRequester())
        }
        temp
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.height(50.dp)
    ) {
        (0 until length).forEach { index ->
            TextField(
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .focusOrder(focusRequester = focusRequesters[index]) {
                        focusRequesters[index + 1].requestFocus()
                    },
                shape = MaterialTheme.shapes.small.copy(all = CornerSize(15.dp)),
                textStyle = MaterialTheme.typography.subtitle1,
                singleLine = true,
                value = code.getOrNull(index = index)?.takeIf {
                    it.isDigit()
                }?.toString() ?: "",
                onValueChange = { value: String ->
                    if (focusRequesters[index].freeFocus()) {
                        val temp = code.toMutableList()
                        if (value == "") {
                            if (temp.size > index) {
                                temp.removeAt(index = index)
                                code = temp
                                focusRequesters.getOrNull(index - 1)?.requestFocus()
                            }
                        } else {
                            if (code.size > index) {
                                temp[index] = value.getOrNull(0) ?: ' '
                            } else {
                                temp.add(value.getOrNull(0) ?: ' ')
                                code = temp
                                focusRequesters.getOrNull(index + 1)?.requestFocus() ?: onFilled(
                                    code.joinToString(separator = "")
                                )
                            }
                        }
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )

            Spacer(modifier = Modifier.width(15.dp))
        }
    }
}

@Composable
fun OtpCell(
    modifier: Modifier = Modifier,
    value: String,
    isCursorVisible: Boolean = false
) {

    val scope = rememberCoroutineScope()
    val (cursorSymbol, setCursorSymbol) = remember { mutableStateOf("") }

    // Cursor blinking logic
    LaunchedEffect(key1 = cursorSymbol, isCursorVisible) {
        if (isCursorVisible) {
            scope.launch {
                delay(350)
                setCursorSymbol(if (cursorSymbol.isEmpty()) "|" else "")
            }
        }
    }

    // UI for OTP Character
    Box(
        modifier = modifier
    ) {
        Text(
            text = if (isCursorVisible) cursorSymbol else value,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@ExperimentalComposeUiApi
@Composable
fun OtpBugView(modifier: Modifier = Modifier) {
    val (editValue, setEditValue) = remember { mutableStateOf("") }
    val otpLength = remember { 4 }
    val focusRequester = remember { FocusRequester() }
    val keyboard = LocalSoftwareKeyboardController.current

    // hidden TextField for controlling OTP Cells UI
    TextField(
        value = editValue,
        onValueChange = {
            if (it.length <= otpLength) {
                setEditValue(it)
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
                    .padding(10.dp)
                    .size(50.dp)
                    .clickable {
                        focusRequester.requestFocus()
                        // this is required so if keyboard is dissmissed
                        // then could be open again with focus
                        keyboard?.show()
                    }
                    .background(MaterialTheme.colors.primary.copy(alpha = 0.3f), shape = MaterialTheme.shapes.small),
                value = editValue.getOrNull(index)?.toString() ?: "",
                isCursorVisible = editValue.length == index
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

@ExperimentalComposeUiApi
@Preview(name = "otp preview", showBackground = true)
@Composable
fun DefaultOtpPreview() {
    FlunaceTheme {
        OtpBugView()
    }
}

