package com.kamabd.exchangeratesapp.ui.features.favorites

import com.kamabd.base.BaseVm
import com.kamabd.domain.CurrencyInfo
import com.kamabd.i_currencies.use_case.RemoveCurrencyFromFavoritesUseCase
import com.kamabd.i_currencies.use_case.SubscribeToFavoriteCurrenciesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val subscribeToFavoriteCurrenciesUseCase: SubscribeToFavoriteCurrenciesUseCase,
    private val removeCurrencyFromFavoritesUseCase: RemoveCurrencyFromFavoritesUseCase,
) : BaseVm() {

    private val _uiState = MutableStateFlow<FavoritesState>(FavoritesState.Idle)
    val uiState: StateFlow<FavoritesState>
        get() = _uiState

    init {
        launch {
            subscribeToFavoriteCurrenciesUseCase()
                .collect {
                    it.handleResult { favoriteItems ->
                        if (favoriteItems.isEmpty()) {
                            _uiState.value = FavoritesState.Empty
                        } else {
                            _uiState.value = FavoritesState.Success(favoriteItems)
                        }
                    }
                }
        }
    }

    fun onFavoriteCurrencyClicked(currencyInfo: CurrencyInfo) {
        launch {
            removeCurrencyFromFavoritesUseCase(
                RemoveCurrencyFromFavoritesUseCase.Params(
                    currencyInfo
                )
            )
        }
    }
}