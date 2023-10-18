package com.kamabd.exchangeratesapp.ui.features.currencies.data

import com.kamabd.domain.CurrenciesLatestInfo
import com.kamabd.domain.CurrencyInfo

val currenciesMockData: CurrenciesLatestInfo
    get() = CurrenciesLatestInfo(
        base = "EUR",
        date = "2023-10-17",
        timestamp = 1697573583,
        currencies = currenciesMockItems,
    )

val currenciesMockItems: List<CurrencyInfo>
    get() = listOf(
        CurrencyInfo(
            currencyCode = "AED",
            currencyValue = 3.88421,
            false
        ),
        CurrencyInfo(
            currencyCode = "USD",
            currencyValue = 1.057507,
            false
        ),
        CurrencyInfo(
            currencyCode = "BYN",
            currencyValue = 3.479637,
            false
        ),
        CurrencyInfo(
            currencyCode = "RUB",
            currencyValue = 103.794479,
            false
        ),
    )

val defaultBaseCurrency = "EUR"
val defaultCurrencySymbols = listOf(
    "EUR",
    "USD",
    "AED",
    "BYN",
    "RUB",
    "JPY",
)