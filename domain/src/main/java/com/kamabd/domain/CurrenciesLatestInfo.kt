package com.kamabd.domain

data class CurrenciesLatestInfo(
    val base: String,
    val date: String = "",
    val timestamp: Long = 0L,
    val currencies: List<CurrencyInfo> = emptyList(),
)