package com.enike.flunace.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp




@Composable
fun DefaultButton(
    buttonText: String,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 40.dp),
    buttonClicked: () -> Unit
) {
    Button(
        modifier = modifier,
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 8.dp,
            disabledElevation = 0.dp
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