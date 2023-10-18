package com.kamabd.uikit.utils

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.kamabd.uikit.paletteBlack
import com.kamabd.uikit.paletteWhite

fun Modifier.drawBorderIfNeeded(
    shape: CornerBasedShape,
    borderColor: Brush? = null,
    borderWidthDp: Dp? = null,
): Modifier {
    return if (borderColor != null && borderWidthDp != null) {
        border(
            width = borderWidthDp,
            brush = borderColor,
            shape = shape
        )
    } else {
        this
    }
}

fun Modifier.clickableWithRipple(
    rippleColor: Color = paletteBlack.copy(alpha = 0.2F),
    rippleNightColor: Color = paletteWhite.copy(alpha = 0.2F),
    enabled: Boolean = true,
    onClick: () -> Unit
): Modifier = composed {
    val isNight = false // isSystemInDarkTheme() todo
    val color = if (isNight) {
        rippleNightColor
    } else {
        rippleColor
    }
    clickable(
        enabled = enabled,
        indication = rememberRipple(color = color),
        interactionSource = remember { MutableInteractionSource() },
        onClick = onClick
    )
}