package com.kamabd.uikit.theme.impl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kamabd.uikit.interFonts
import com.kamabd.uikit.theme.api.AppThemeTypography

val localTypography: AppThemeTypography
    @Composable
    get() = LocalAppTheme.current.typography

@Immutable
object DefaultAppTypography : AppThemeTypography {

    override val title1: TextStyle = TextStyle(
        fontFamily = interFonts,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp
    )

    override val body1: TextStyle = TextStyle(
        fontFamily = interFonts,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    )

    override val body2: TextStyle = TextStyle(
        fontFamily = interFonts,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    )

    override val body3: TextStyle = TextStyle(
        fontFamily = interFonts,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    )

    override val body4: TextStyle = TextStyle(
        fontFamily = interFonts,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp
    )

    override val caption: TextStyle = TextStyle(
        fontFamily = interFonts,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    )

    override val caption2: TextStyle = TextStyle(
        fontFamily = interFonts,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp
    )

    override val caption3: TextStyle = TextStyle(
        fontFamily = interFonts,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp
    )
}