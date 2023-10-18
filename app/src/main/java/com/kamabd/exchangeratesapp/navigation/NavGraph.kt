package com.kamabd.exchangeratesapp.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.kamabd.exchangeratesapp.R
import com.kamabd.exchangeratesapp.ui.features.currencies.CurrenciesScreen
import com.kamabd.exchangeratesapp.ui.features.favorites.FavoritesScreen
import com.kamabd.exchangeratesapp.ui.features.filters.FiltersScreen

@Composable
fun Navigation(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Currencies.route,
        modifier = modifier,
    ) {
        composable(Screen.Currencies.route) {
            CurrenciesScreen(
                navController = navController
            )
        }
        composable(Screen.Favorites.route) {
            FavoritesScreen(
                navController = navController
            )
        }
        composable(Screen.Filters.route) {
            FiltersScreen(
                navController = navController
            )
        }
    }
}

@Composable
fun navigationTitle(navController: NavController): String {
    return when (currentRoute(navController)) {
        Screen.Currencies.route -> stringResource(id = R.string.currencies_title)
        Screen.Favorites.route -> stringResource(id = R.string.favorites_title)
        Screen.Filters.route -> stringResource(id = R.string.filters_title)
        else -> {
            ""
        }
    }
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route?.substringBeforeLast("/")
}