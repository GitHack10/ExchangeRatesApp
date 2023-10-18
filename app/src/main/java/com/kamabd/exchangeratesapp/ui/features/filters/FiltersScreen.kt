package com.kamabd.exchangeratesapp.ui.features.filters

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kamabd.exchangeratesapp.R
import com.kamabd.exchangeratesapp.ui.features.filters.content.FiltersContent

@Composable
fun FiltersScreen(
    navController: NavController,
    vm: FiltersViewModel = hiltViewModel()
) {

    when (val state = vm.uiState.collectAsState().value) {
        is FiltersState.Loading -> {
            // todo loading state
        }
        is FiltersState.Success -> {
            FiltersContent(
                title = stringResource(id = R.string.filters_title),
                items = state.items,
                pressOnBack = {
                    navController.popBackStack()
                }
            )
        }
        else -> {
        }
    }
}
