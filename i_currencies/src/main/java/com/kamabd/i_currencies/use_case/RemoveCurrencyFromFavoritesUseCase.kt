package com.kamabd.i_currencies.use_case

import com.kamabd.base.coroutines.RequestResult
import com.kamabd.base.coroutines.wrapResult
import com.kamabd.data.favorite_currencies.FavoriteCurrenciesDao
import com.kamabd.domain.CurrencyInfo
import com.kamabd.i_currencies.mapToFavoriteCurrencyDTO
import com.kamabd.network.OneInputResultUseCase
import com.kamabd.network.result_handler.RequestResultHandler
import javax.inject.Inject

class RemoveCurrencyFromFavoritesUseCase @Inject constructor(
    requestResultHandler: RequestResultHandler,
    private val favoriteCurrenciesDao: FavoriteCurrenciesDao
) : OneInputResultUseCase<RemoveCurrencyFromFavoritesUseCase.Params, Unit>(),
    RequestResultHandler by requestResultHandler {

    override suspend fun invoke(input: Params): RequestResult<Unit> = wrapResult {
        favoriteCurrenciesDao.deleteFavoriteCurrency(
            currency = input.currencyInfo.mapToFavoriteCurrencyDTO()
        )
    }

    class Params(
        val currencyInfo: CurrencyInfo
    )
}