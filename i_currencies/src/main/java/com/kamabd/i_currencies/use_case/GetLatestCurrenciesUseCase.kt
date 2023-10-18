package com.kamabd.i_currencies.use_case

import com.kamabd.base.coroutines.RequestResult
import com.kamabd.base.coroutines.map
import com.kamabd.base.coroutines.resultOrDefault
import com.kamabd.base.coroutines.wrapResult
import com.kamabd.data.favorite_currencies.FavoriteCurrencyDTO
import com.kamabd.data.favorite_currencies.FavoriteCurrenciesDao
import com.kamabd.domain.CurrenciesLatestInfo
import com.kamabd.i_currencies.api.ApiCurrencies
import com.kamabd.network.OneInputFlowResultUseCase
import com.kamabd.network.result_handler.RequestResultHandler
import com.kamabd.network.use_case.RequestData
import com.kamabd.network.use_case.RequestDataDelegate
import com.kamabd.network.use_case.RequestType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLatestCurrenciesUseCase @Inject constructor(
    requestResultHandler: RequestResultHandler,
    requestDataDelegate: RequestDataDelegate,
    private val apiCurrencies: ApiCurrencies,
    private val favoriteCurrenciesDao: FavoriteCurrenciesDao,
) : OneInputFlowResultUseCase<GetLatestCurrenciesUseCase.Params, RequestData<CurrenciesLatestInfo>>(),
    RequestResultHandler by requestResultHandler,
    RequestDataDelegate by requestDataDelegate {

    override suspend fun invoke(input: Params): Flow<RequestResult<RequestData<CurrenciesLatestInfo>>> = makeRequest(
        requestType = RequestType.RemoteOnly,
        remoteSource = {
            val favoriteCurrenciesIds = favoriteCurrenciesDao
                .getFavoriteCurrencies()
                .map { it.currencyCode }
            callAndMap {
                apiCurrencies.getLatestCurrenciesInfo(
                    input.base,
                    input.symbols.joinToString(","),
                )
            }
                .map {
                    it.copy(
                        currencies = it.currencies.map { item ->
                            item.copy(
                                isFavorite = favoriteCurrenciesIds.contains(item.currencyCode)
                            )
                        }
                    )
                }
        },
        updateLocal = { result ->
            val localItems = favoriteCurrenciesDao.getFavoriteCurrencies()
            val remoteItems = result.resultOrDefault(null)
                ?.currencies
            if (!remoteItems.isNullOrEmpty()) {
                val newItems = arrayListOf<FavoriteCurrencyDTO>()
                localItems.forEach { localItem ->
                    val newCurrencyValue = remoteItems
                        .firstOrNull { it.currencyCode == localItem.currencyCode }
                        ?.currencyValue
                    if (newCurrencyValue != null) {
                        newItems.add(localItem.copy(currencyValue = newCurrencyValue))
                    }
                }
                favoriteCurrenciesDao.insertAllFavoriteCurrencies(newItems)
            }
        },
        localSource = {
            wrapResult {
                CurrenciesLatestInfo(
                    input.base,
                )
            }
        }
    )

    class Params(
        val base: String,
        val symbols: List<String>
    )
}