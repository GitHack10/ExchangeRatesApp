package com.kamabd.exchangeratesapp.ui.features.currencies

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kamabd.exchangeratesapp.R
import com.kamabd.exchangeratesapp.navigation.Screen
import com.kamabd.exchangeratesapp.ui.component.LoadingContent
import com.kamabd.exchangeratesapp.ui.features.currencies.content.CurrenciesContent
import com.kamabd.exchangeratesapp.ui.features.currencies.content.CurrenciesHeaderContent
import com.kamabd.exchangeratesapp.ui.features.currencies.data.defaultBaseCurrency
import com.kamabd.exchangeratesapp.ui.features.filters.data.SortBy
import com.kamabd.logger.AppLogger

@Composable
fun CurrenciesScreen(
    navController: NavController,
    selectedSortBy: SortBy,
    vm: CurrenciesViewModel = hiltViewModel()
) {
    vm.setSelectedSortBy(selectedSortBy)
    val state = vm.uiState.collectAsState(initial = CurrenciesState.Idle).value
    val baseCurrency = (state as? CurrenciesState.Success)?.baseCurrency ?: defaultBaseCurrency
    AppLogger.logD("init screen: $baseCurrency")
    Column {
        CurrenciesHeaderContent(
            title = stringResource(id = R.string.currencies_title),
            onBaseCurrencySelected = vm::onBaseCurrencySelected,
            onOpenFiltersClicked = {
                navController.currentBackStackEntry
                    ?.arguments
                    ?.putParcelable(Screen.Filters.objectName, selectedSortBy)
                navController.navigate(Screen.Filters.route.plus("/${selectedSortBy}"))
            }
        )
        when (state) {
            is CurrenciesState.Loading -> {
                LoadingContent()
            }
            is CurrenciesState.Success -> {
                CurrenciesContent(
                    data = state.data,
                    onFavoriteIconClicked = vm::onFavoriteIconClicked,
                )
            }
            else -> {
            }
        }
    }
}