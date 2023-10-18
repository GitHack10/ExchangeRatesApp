package com.kamabd.uikit.components.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import com.kamabd.uikit.components.ComponentShapeParams
import com.kamabd.uikit.components.componentShapeParams
import com.kamabd.uikit.paletteBlack
import com.kamabd.uikit.theme.impl.localColors
import com.kamabd.uikit.theme.impl.localTypography

//region primary
@Composable
fun primaryAppButtonTheme(
    colors: AppButtonColors = primaryUiButtonColors(),
    textStyle: TextStyle = localTypography.body2,
    shapeParams: ComponentShapeParams = componentShapeParams(),
): AppButtonTheme = AppButtonTheme(
    colors = colors,
    textStyle = textStyle,
    shapeParams = shapeParams,
)

@Composable
fun primaryUiButtonColors(): AppButtonColors = AppButtonColors(
    rippleColor = paletteBlack.copy(alpha = .5f),
    enabledBgColor = SolidColor(localColors.btnColors.btnPrimary),
    disabledBgColor = SolidColor(localColors.btnColors.btnPrimary.copy(alpha = 0.5F)),
    enabledTextColor = SolidColor(localColors.textColors.textInverted),
    disabledTextColor = SolidColor(localColors.textColors.textInverted.copy(alpha = 0.5F)),
)
//endregion