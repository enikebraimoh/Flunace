package com.enike.flunace.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
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