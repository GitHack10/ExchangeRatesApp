package com.kamabd.i_currencies.api.response

import com.kamabd.base.transformable.Transformable
import com.kamabd.domain.ConvertedInfo
import com.kamabd.domain.CurrenciesConvertedInfo
import com.kamabd.domain.QueryInfo

class CurrenciesConvertedResponseObj(
    val date: String,
    val result: Double,
    val info: ConvertedResponseObj,
    val query: QueryResponseObj
) : Transformable<CurrenciesConvertedInfo> {

    override fun transform(): CurrenciesConvertedInfo =
        CurrenciesConvertedInfo(
            date = date,
            result = result,
            info = info.transform(),
            query = query.transform(),
        )
}

class ConvertedResponseObj(
    val rate: Double,
    val timestamp: Long,
) : Transformable<ConvertedInfo> {

    override fun transform(): ConvertedInfo =
        ConvertedInfo(
            rate = rate,
            timestamp = timestamp,
        )
}

class QueryResponseObj(
    val amount: Double,
    val from: String,
    val to: String,
) : Transformable<QueryInfo> {

    override fun transform(): QueryInfo =
        QueryInfo(
            amount = amount,
            from = from,
            to = to,
        )
}