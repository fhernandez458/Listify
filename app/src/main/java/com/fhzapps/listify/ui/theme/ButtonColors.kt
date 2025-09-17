package com.fhzapps.listify.ui.theme

import androidx.compose.material3.ButtonColors

fun goodButton(): ButtonColors {
    return ButtonColors(
        containerColor = androidx.compose.ui.graphics.Color.Blue,
        contentColor = androidx.compose.ui.graphics.Color.White,
        disabledContainerColor = androidx.compose.ui.graphics.Color.Gray,
        disabledContentColor = androidx.compose.ui.graphics.Color.Black
    )
}

fun badButton() : ButtonColors {
    return ButtonColors(
        containerColor = androidx.compose.ui.graphics.Color.Red,
        contentColor = androidx.compose.ui.graphics.Color.White,
        disabledContainerColor = androidx.compose.ui.graphics.Color.Gray,
        disabledContentColor = androidx.compose.ui.graphics.Color.Black
    )
}