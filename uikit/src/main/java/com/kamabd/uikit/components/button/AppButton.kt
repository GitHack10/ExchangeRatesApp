package com.kamabd.uikit.components.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.kamabd.uikit.utils.drawBorderIfNeeded

val appButtonHeight = 52.dp

@Composable
fun AppButton(
    state: AppButtonState,
    theme: AppButtonTheme,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .height(appButtonHeight)
            .clip(theme.shapeParams.shape)
            .background(
                if (state.useEnabledColors) {
                    theme.colors.enabledBgColor
                } else {
                    theme.colors.disabledBgColor
                }
            )
            .drawBorderIfNeeded(
                theme.shapeParams.shape,
                theme.shapeParams.borderColor,
                theme.shapeParams.borderWidthDp,
            )
            .clickable(
                enabled = state.isClickEnabled,
                indication = rememberRipple(color = theme.colors.rippleColor),
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            val text = state.text.stringText(LocalContext.current.resources)
            Text(
                text = text,
                style = theme.textStyle.copy(
                    brush = if (state.useEnabledColors) {
                        theme.colors.enabledTextColor
                    } else {
                        theme.colors.disabledTextColor
                    }
                )
            )
        }
    }
}