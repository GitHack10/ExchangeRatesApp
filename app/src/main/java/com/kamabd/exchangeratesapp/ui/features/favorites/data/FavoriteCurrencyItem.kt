package com.kamabd.exchangeratesapp.ui.features.favorites.data

data class FavoriteCurrencyItem(
    val currencyCode: String,
    val currencyValue: Double,
    val isFavorite: Boolean,
)