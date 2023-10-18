package com.kamabd.exchangeratesapp.ui.features.favorites

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kamabd.exchangeratesapp.navigation.Screen
import com.kamabd.exchangeratesapp.navigation.navigationTitle
import com.kamabd.exchangeratesapp.ui.features.favorites.content.FavoritesContent
import com.kamabd.exchangeratesapp.ui.features.favorites.content.FavoritesEmptyContent
import com.kamabd.exchangeratesapp.ui.features.favorites.content.FavoritesHeaderContent

@Composable
fun FavoritesScreen(
    navController: NavController,
    vm: FavoritesViewModel = hiltViewModel()
) {
    Column {
        FavoritesHeaderContent(
            title = navigationTitle(navController = navController)
        )
        when (val state = vm.uiState.collectAsState().value) {
            is FavoritesState.Loading -> {
                // todo loading state
            }
            is FavoritesState.Empty -> {
                FavoritesEmptyContent(
                    onAddCurrencyClicked = {
                        navController.navigate(Screen.CurrenciesNav.route)
                    }
                )
            }
            is FavoritesState.Success -> {
                FavoritesContent(
                    items = state.items,
                    onFavoriteIconClicked = vm::onFavoriteCurrencyClicked,
                )
            }
            else -> {
            }
        }
    }
    // todo favorite screen content
}
