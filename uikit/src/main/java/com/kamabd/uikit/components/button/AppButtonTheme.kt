package com.kamabd.uikit.components.button

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.kamabd.uikit.components.ComponentShapeParams

@Stable
data class AppButtonTheme(
    val colors: AppButtonColors,
    val textStyle: TextStyle,
    val shapeParams: ComponentShapeParams,
)

@Stable
data class AppButtonColors(
    val rippleColor: Color,
    val enabledBgColor: Brush,
    val disabledBgColor: Brush,
    val enabledTextColor: Brush,
    val disabledTextColor: Brush
)