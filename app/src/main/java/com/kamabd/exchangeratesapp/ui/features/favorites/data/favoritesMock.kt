package com.kamabd.exchangeratesapp.ui.features.favorites.data

import com.kamabd.domain.CurrencyInfo

val favoritesCurrenciesMockItems: List<CurrencyInfo>
    get() = listOf(
        CurrencyInfo(
            currencyCode = "AED",
            currencyValue = 3.88421,
            true
        ),
        CurrencyInfo(
            currencyCode = "USD",
            currencyValue = 1.057507,
            true
        )
    )