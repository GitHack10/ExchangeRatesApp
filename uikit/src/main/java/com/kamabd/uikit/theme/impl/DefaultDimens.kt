package com.kamabd.uikit.theme.impl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kamabd.uikit.theme.api.AppThemeDimens

val localDimens: AppThemeDimens
    @Composable
    get() = LocalAppTheme.current.dimens

@Immutable
object DefaultDimens : AppThemeDimens {
    override val spacing_2: Dp = 2.dp
    override val spacing_4: Dp = 4.dp
    override val spacing_6: Dp = 6.dp
    override val spacing_8: Dp = 8.dp
    override val spacing_12: Dp = 12.dp
    override val spacing_16: Dp = 16.dp
    override val spacing_20: Dp = 20.dp
    override val spacing_24: Dp = 24.dp
    override val spacing_28: Dp = 28.dp
    override val spacing_32: Dp = 32.dp
    override val spacing_40: Dp = 40.dp
    override val spacing_48: Dp = 48.dp
    override val spacing_56: Dp = 56.dp
    override val spacing_72: Dp = 72.dp
}