package com.kamabd.i_currencies.use_case

import com.kamabd.base.coroutines.RequestResult
import com.kamabd.data.favorite_currencies.FavoriteCurrenciesDao
import com.kamabd.data.favorite_currencies.FavoriteCurrencyDTO
import com.kamabd.domain.CurrencyInfo
import com.kamabd.i_currencies.mapToCurrencyInfo
import com.kamabd.network.NoInputsFlowResultUseCase
import com.kamabd.network.result_handler.RequestResultHandler
import com.kamabd.network.use_case.RequestDataDelegate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class SubscribeToFavoriteCurrenciesUseCase @Inject constructor(
    requestResultHandler: RequestResultHandler,
    requestDataDelegate: RequestDataDelegate,
    private val favoriteCurrenciesDao: FavoriteCurrenciesDao,
) : NoInputsFlowResultUseCase<List<CurrencyInfo>>(),
    RequestResultHandler by requestResultHandler,
    RequestDataDelegate by requestDataDelegate {

    override suspend fun invoke(): Flow<RequestResult<List<CurrencyInfo>>> = channelFlow {
        favoriteCurrenciesDao.subscribeToFavoriteCurrencies()
            .collect {
                trySend(
                    RequestResult.Success(it.map(FavoriteCurrencyDTO::mapToCurrencyInfo))
                )
            }
    }
}