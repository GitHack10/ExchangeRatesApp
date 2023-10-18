package com.kamabd.uikit.theme.impl

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import com.kamabd.uikit.theme.api.AppTheme
import com.kamabd.uikit.theme.api.AppThemeColors
import com.kamabd.uikit.theme.api.AppThemeDimens
import com.kamabd.uikit.theme.api.AppThemeShapes
import com.kamabd.uikit.theme.api.AppThemeTypography

val LocalAppTheme: ProvidableCompositionLocal<AppTheme> = compositionLocalOf {
    DayAppTheme
}

val localColors: AppThemeColors
    @Composable
    get() = LocalAppTheme.current.colors

object DayAppTheme : AppTheme {

    override val dimens: AppThemeDimens = DefaultDimens

    override val typography: AppThemeTypography = DefaultAppTypography

    override val colors: AppThemeColors = DefaultAppDayColors

    override val shapes: AppThemeShapes = DefaultAppShapes
}

object NightAppTheme : AppTheme {

    override val dimens: AppThemeDimens = DefaultDimens

    override val typography: AppThemeTypography = DefaultAppTypography

    override val colors: AppThemeColors = DefaultAppNightColors

    override val shapes: AppThemeShapes = DefaultAppShapes
}

@Composable
fun AppTheme(
    isInNightMode: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalAppTheme provides appTheme(isInNightMode)
    ) {
        content()
    }
}

private fun appTheme(
    inNightMode: Boolean
): AppTheme = if (inNightMode) {
    NightAppTheme
} else {
    DayAppTheme
}
