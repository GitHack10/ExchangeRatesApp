package com.kamabd.i_currencies.api

import com.kamabd.i_currencies.URL_CONVERT
import com.kamabd.i_currencies.URL_LATEST
import com.kamabd.i_currencies.api.response.CurrenciesConvertedResponseObj
import com.kamabd.i_currencies.api.response.CurrenciesLatestResponseObj
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCurrencies {

    @GET(URL_LATEST)
    suspend fun getLatestCurrenciesInfo(
        @Query("base") base: String,
        @Query("symbols") symbols: String
    ): CurrenciesLatestResponseObj

    @GET(URL_CONVERT)
    suspend fun getConvertedCurrenciesInfo(
        @Query("to") to: String,
        @Query("from") from: String,
        @Query("amount") amount: Double,
    ): CurrenciesConvertedResponseObj
}