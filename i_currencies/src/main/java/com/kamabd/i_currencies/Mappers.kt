package com.kamabd.i_currencies

import com.kamabd.data.favorite_currencies.FavoriteCurrencyDTO
import com.kamabd.domain.CurrencyInfo

fun CurrencyInfo.mapToFavoriteCurrencyDTO(): FavoriteCurrencyDTO = FavoriteCurrencyDTO(
    currencyCode,
    currencyValue,
)

fun FavoriteCurrencyDTO.mapToCurrencyInfo(): CurrencyInfo = CurrencyInfo(
    currencyCode,
    currencyValue,
    true
)