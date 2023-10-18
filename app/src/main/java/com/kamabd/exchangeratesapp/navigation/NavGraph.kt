package com.kamabd.exchangeratesapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.kamabd.exchangeratesapp.R
import com.kamabd.exchangeratesapp.ui.features.currencies.CurrenciesScreen
import com.kamabd.exchangeratesapp.ui.features.favorites.FavoritesScreen
import com.kamabd.exchangeratesapp.ui.features.filters.FiltersScreen
import com.kamabd.exchangeratesapp.ui.features.filters.data.SortBy
import com.kamabd.logger.AppLogger

@Composable
fun Navigation(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val selectedSortBy = remember {
        mutableStateOf(SortBy.CodeAsc)
    }
    NavHost(
        navController = navController,
        startDestination = Screen.Currencies.route,
        modifier = modifier,
    ) {
        composable(Screen.Currencies.route) { navBackResult ->
            selectedSortBy.value = navBackResult.savedStateHandle[Screen.Filters.objectName] ?: SortBy.CodeAsc
            AppLogger.logD("navBackResult: ${selectedSortBy.value}")
            CurrenciesScreen(
                navController = navController,
                selectedSortBy = selectedSortBy.value,
            )
        }
        composable(Screen.Favorites.route) {
            FavoritesScreen(
                navController = navController
            )
        }
        composable(
            Screen.Filters.route.plus(Screen.Filters.objectPath),
            arguments = listOf(navArgument(Screen.Filters.objectName) {
                type = NavType.EnumType(SortBy::class.java)
            })
        ) {
            val sortBy = it.arguments?.getParcelable(Screen.Filters.objectName) ?: SortBy.CodeAsc
            FiltersScreen(
                navController = navController,
                sortBy = sortBy,
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