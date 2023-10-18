package com.kamabd.exchangeratesapp.navigation

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.kamabd.exchangeratesapp.R
import com.kamabd.uikit.theme.impl.localColors
import com.kamabd.uikit.theme.impl.localDimens
import com.kamabd.uikit.theme.impl.localShapes

sealed class Screen(
    val route: String,
    @StringRes val title: Int = R.string.app_name,
    val navIcon: (@Composable (Boolean) -> Unit) = {
        Icon(
            painter = painterResource(id = R.drawable.ic_svg_currencies),
            tint = localColors.iconsColors.iconPrimary,
            contentDescription = null,
        )
    },
    val objectName: String = "",
    val objectPath: String = ""
) {

    object Currencies : Screen(
        route = "currencies_screen",
        title = R.string.currencies_title,
    )

    object Favorites : Screen(
        route = "favorites_screen",
        title = R.string.favorites_title,
    )

    object Filters : Screen(
        route = "filters_screen",
        title = R.string.filters_title,
        objectName = "sortBy",
        objectPath = "/{sortBy}",
    )

    // Bottom Navigation
    object CurrenciesNav : Screen(
        route = "currencies_screen",
        title = R.string.currencies_title,
        navIcon = { selected ->
            val bgColor = if (selected) {
                localColors.bgColors.bgColorLightPrimary
            } else {
                Color.Transparent
            }
            Box(
                Modifier
                    .clip(localShapes.large)
                    .background(bgColor)
                    .padding(
                        horizontal = localDimens.spacing_20,
                        vertical = localDimens.spacing_4
                    )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_svg_currencies),
                    contentDescription = "search"
                )
            }
        }
    )

    object FavoritesNav : Screen(
        route = "favorites_screen",
        title = R.string.favorites_title,
        navIcon = { selected ->
            val bgColor = if (selected) {
                localColors.bgColors.bgColorLightPrimary
            } else {
                Color.Transparent
            }
            Box(
                Modifier
                    .clip(localShapes.large)
                    .background(bgColor)
                    .padding(
                        horizontal = localDimens.spacing_20,
                        vertical = localDimens.spacing_4
                    )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_svg_favorites_filled),
                    contentDescription = "search"
                )
            }
        }
    )
}