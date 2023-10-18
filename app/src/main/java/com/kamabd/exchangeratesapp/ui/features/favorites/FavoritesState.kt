package com.kamabd.exchangeratesapp.ui.features.favorites

import com.kamabd.domain.CurrencyInfo

sealed class FavoritesState {

    object Idle : FavoritesState()

    object Loading : FavoritesState()

    object Empty : FavoritesState()

    data class Success(
        val items: List<CurrencyInfo>
    ) : FavoritesState()
}