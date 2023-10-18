package com.kamabd.exchangeratesapp.ui.features.filters

import com.kamabd.exchangeratesapp.ui.features.filters.data.FilterItem

sealed class FiltersState {

    object Idle : FiltersState()

    object Loading : FiltersState()

    data class Success(
        val items: List<FilterItem>
    ) : FiltersState()
}