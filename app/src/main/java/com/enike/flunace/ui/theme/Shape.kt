package com.enike.flunace.ui.theme

import android.widget.Button
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)

val myshapes = Shapes(
    small = RoundedCornerShape(percent = 50),
    medium = RoundedCornerShape(size = 40.dp)
)