package com.kamabd.exchangeratesapp.ui.features.filters

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kamabd.exchangeratesapp.R
import com.kamabd.exchangeratesapp.navigation.Screen
import com.kamabd.exchangeratesapp.ui.features.filters.content.FiltersContent
import com.kamabd.exchangeratesapp.ui.features.filters.data.SortBy
import com.kamabd.logger.AppLogger

@Composable
fun FiltersScreen(
    navController: NavController,
    sortBy: SortBy,
    vm: FiltersViewModel = hiltViewModel()
) {
    LaunchedEffect(true) {
        vm.updateSortBy(sortBy)
    }
    when (val state = vm.uiState.collectAsState().value) {
        is FiltersState.Loading -> {
            // todo loading state
        }

        is FiltersState.Success -> {
            FiltersContent(
                title = stringResource(id = R.string.filters_title),
                state = state,
                onFilterItemClicked = vm::updateSortBy,
                onApplyButtonClicked = {
                    AppLogger.logD("onApplyButtonClicked: ${state.selectedSortBy}")
                    navController.popBackStack()
                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.set(Screen.Filters.objectName, state.selectedSortBy)
                },
                pressOnBack = {
                    navController.popBackStack()
                }
            )
        }

        else -> {
        }
    }
}
