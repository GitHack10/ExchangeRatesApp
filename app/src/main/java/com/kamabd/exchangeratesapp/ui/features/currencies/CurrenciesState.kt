package com.kamabd.exchangeratesapp.ui.features.currencies

import com.kamabd.domain.CurrenciesLatestInfo

sealed class CurrenciesState {

    object Idle : CurrenciesState()

    object Loading : CurrenciesState()

    data class Success(
        val data: CurrenciesLatestInfo,
        val baseCurrency: String
    ) : CurrenciesState()
}
