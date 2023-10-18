package com.kamabd.i_currencies.api.response

import com.kamabd.base.transformable.Transformable
import com.kamabd.domain.CurrenciesLatestInfo
import com.kamabd.domain.CurrencyInfo

class CurrenciesLatestResponseObj(
    val base: String,
    val date: String,
    val timestamp: Long,
    val rates: HashMap<String, Double>
) : Transformable<CurrenciesLatestInfo> {

    override fun transform(): CurrenciesLatestInfo = CurrenciesLatestInfo(
        base = base,
        date = date,
        timestamp = timestamp,
        currencies = rates.map {
            CurrencyInfo(
                it.key,
                it.value
            )
        }
    )
}