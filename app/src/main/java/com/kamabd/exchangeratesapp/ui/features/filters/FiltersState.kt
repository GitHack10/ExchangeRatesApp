package com.kamabd.exchangeratesapp.ui.features.filters

import com.kamabd.exchangeratesapp.ui.features.filters.data.SortBy
import com.kamabd.exchangeratesapp.ui.features.filters.data.filterMockItems

sealed class FiltersState {

    object Idle : FiltersState()

    object Loading : FiltersState()

    data class Success(
        val items: List<SortBy> = filterMockItems,
        val selectedSortBy: SortBy = SortBy.CodeAsc
    ) : FiltersState()
}