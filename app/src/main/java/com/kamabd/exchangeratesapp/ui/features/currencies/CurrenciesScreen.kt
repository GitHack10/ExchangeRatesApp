package com.kamabd.exchangeratesapp.ui.features.currencies

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.kamabd.exchangeratesapp.R
import com.kamabd.exchangeratesapp.navigation.Screen
import com.kamabd.exchangeratesapp.ui.component.LoadingContent
import com.kamabd.exchangeratesapp.ui.features.currencies.content.CurrenciesContent
import com.kamabd.exchangeratesapp.ui.features.currencies.content.CurrenciesHeaderContent

@Composable
fun CurrenciesScreen(
    navController: NavController,
    vm: CurrenciesViewModel = hiltViewModel()
) {
    Column {
        CurrenciesHeaderContent(
            title = stringResource(id = R.string.currencies_title),
            onBaseCurrencySelected = vm::onBaseCurrencySelected,
            onOpenFiltersClicked = {
                val navOptions = NavOptions.Builder()
                    .setEnterAnim(R.anim.scale_fade_in)
                    .setExitAnim(R.anim.scale_fade_out)
                    .setPopEnterAnim(R.anim.scale_fade_in)
                    .setPopExitAnim(R.anim.scale_fade_out)
                    .build()
                navController.navigate(Screen.Filters.route, navOptions)
            }
        )
        when (val state = vm.uiState.collectAsState(initial = CurrenciesState.Idle).value) {
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