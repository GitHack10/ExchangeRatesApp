package com.kamabd.uikit.theme.api

import androidx.compose.ui.graphics.Color

interface AppThemeColors {

    val textColors: ThemeTextColors

    val bgColors: ThemeBgColors

    val btnColors: ThemeButtonColors

    val iconsColors: ThemeIconsColors
}

interface ThemeIconsColors {

    val iconSecondary: Color

    val iconPrimary: Color

    val iconYellow: Color
}

interface ThemeTextColors {

    val textInverted: Color

    val textPrimary: Color

    val textSecondary: Color

    val textSolid: Color
}

interface ThemeBgColors {

    val bgColorPrimary: Color

    val bgColorSecondary: Color

    val bgInverted: Color

    val bgColorHeader: Color

    val bgColorBorder: Color

    val bgColorOutline: Color

    val bgColorLightPrimary: Color
}

interface ThemeButtonColors {

    val btnPrimary: Color

    val btnSecondary: Color

    val btnError: Color
}