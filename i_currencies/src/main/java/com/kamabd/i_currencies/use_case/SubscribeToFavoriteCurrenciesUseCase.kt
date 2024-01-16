package com.kamabd.i_currencies.use_case

import com.kamabd.base.coroutines.RequestResult
import com.kamabd.data.favorite_currencies.FavoriteCurrenciesDao
import com.kamabd.data.favorite_currencies.FavoriteCurrencyDTO
import com.kamabd.domain.CurrencyInfo
import com.kamabd.i_currencies.mapToCurrencyInfo
import com.kamabd.network.NoInputsFlowResultUseCase
import com.kamabd.network.result_handler.RequestResultHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class SubscribeToFavoriteCurrenciesUseCase @Inject constructor(
    requestResultHandler: RequestResultHandler,
    private val favoriteCurrenciesDao: FavoriteCurrenciesDao,
) : NoInputsFlowResultUseCase<List<CurrencyInfo>>(),
    RequestResultHandler by requestResultHandler {

    override suspend fun invoke(): Flow<RequestResult<List<CurrencyInfo>>> =
        favoriteCurrenciesDao.subscribeToFavoriteCurrencies()
            .transform {
                emit(RequestResult.Success(it.map(FavoriteCurrencyDTO::mapToCurrencyInfo)))
            }
}