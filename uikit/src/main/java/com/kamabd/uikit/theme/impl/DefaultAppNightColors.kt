package com.kamabd.uikit.theme.impl

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.kamabd.uikit.paletteBlack
import com.kamabd.uikit.paletteBoulder
import com.kamabd.uikit.paletteCatskillWhite
import com.kamabd.uikit.paletteColdPurple
import com.kamabd.uikit.paletteGallery
import com.kamabd.uikit.paletteLinkWater
import com.kamabd.uikit.palettePersimmon
import com.kamabd.uikit.paletteSelectiveYellow
import com.kamabd.uikit.paletteShipGray
import com.kamabd.uikit.paletteToreaBay
import com.kamabd.uikit.paletteWhite
import com.kamabd.uikit.paletteWildSand
import com.kamabd.uikit.theme.api.AppThemeColors
import com.kamabd.uikit.theme.api.ThemeBgColors
import com.kamabd.uikit.theme.api.ThemeButtonColors
import com.kamabd.uikit.theme.api.ThemeIconsColors
import com.kamabd.uikit.theme.api.ThemeTextColors

@Immutable
object DefaultAppNightColors : AppThemeColors {
    override val textColors: ThemeTextColors = DefaultNightThemeTextColors
    override val bgColors: ThemeBgColors = DefaultNightThemeBgColors
    override val btnColors: ThemeButtonColors = DefaultNightThemeButtonColors
    override val iconsColors: ThemeIconsColors = DefaultNightThemeIconsColors
}

@Immutable
object DefaultNightThemeIconsColors : ThemeIconsColors {

    override val iconPrimary: Color = paletteToreaBay
    override val iconSecondary: Color = paletteColdPurple
    override val iconYellow: Color = paletteSelectiveYellow
}

@Immutable
object DefaultNightThemeTextColors : ThemeTextColors {
    override val textInverted: Color = paletteWhite
    override val textPrimary: Color = paletteShipGray
    override val textSecondary: Color = paletteBoulder
    override val textSolid: Color = paletteBlack
}

@Immutable
object DefaultNightThemeBgColors : ThemeBgColors {
    override val bgColorPrimary: Color = paletteWhite
    override val bgColorSecondary: Color = paletteCatskillWhite
    override val bgInverted: Color = paletteBlack
    override val bgColorHeader: Color = paletteWildSand
    override val bgColorBorder: Color = paletteToreaBay
    override val bgColorOutline: Color = paletteGallery
    override val bgColorLightPrimary: Color = paletteLinkWater
}

@Immutable
object DefaultNightThemeButtonColors : ThemeButtonColors {
    override val btnPrimary: Color = paletteToreaBay
    override val btnSecondary: Color = paletteColdPurple
    override val btnError: Color = palettePersimmon
}