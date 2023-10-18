package com.kamabd.domain

data class CurrencyInfo(
    val currencyCode: String,
    val currencyValue: Double,
    val isFavorite: Boolean = false,
)