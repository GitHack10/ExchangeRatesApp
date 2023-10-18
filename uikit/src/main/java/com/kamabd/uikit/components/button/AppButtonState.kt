package com.kamabd.uikit.components.button

import androidx.compose.runtime.Stable
import com.kamabd.uikit.components.TextSource

@Stable
data class AppButtonState(
    val text: TextSource,
    val actionState: ButtonActionState = ButtonActionState.Enabled
) {

    val useEnabledColors: Boolean
        get() = actionState !is ButtonActionState.Disabled

    val isClickEnabled: Boolean
        get() = actionState is ButtonActionState.Enabled
}

@Stable
sealed class ButtonActionState {

    // without loader, clickable
    @Stable
    object Enabled : ButtonActionState()

    // without loader, not clickable
    @Stable
    object Disabled : ButtonActionState()
}