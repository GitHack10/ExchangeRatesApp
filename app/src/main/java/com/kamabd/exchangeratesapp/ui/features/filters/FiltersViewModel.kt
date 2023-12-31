package com.kamabd.exchangeratesapp.ui.features.filters

import com.kamabd.base.BaseVm
import com.kamabd.exchangeratesapp.ui.features.filters.data.SortBy
import com.kamabd.exchangeratesapp.ui.features.filters.data.filterMockItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class FiltersViewModel @Inject constructor(

) : BaseVm() {

    private val _uiState = MutableStateFlow<FiltersState>(FiltersState.Idle)
    val uiState: StateFlow<FiltersState>
        get() = _uiState

    init {
//        _uiState.value = FiltersState.Success(filterMockItems)
    }

    fun updateSortBy(sortBy: SortBy) {
        _uiState.value = FiltersState.Success(
            items = filterMockItems,
            selectedSortBy = sortBy
        )
    }

    fun onApplyButtonClicked() {
        val selectedSortBy = (uiState.value as? FiltersState.Success)?.selectedSortBy ?: return

    }
}
